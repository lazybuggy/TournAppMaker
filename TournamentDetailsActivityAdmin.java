package f.lucia.tournamentmakerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TournamentDetailsActivityAdmin extends ActionBarActivity {

    TextView title;
    TextView tournType;
    MyGlobals myGlob;
    Tournament tourn;
    Intent intentFromTeam;
    ArrayList<String> ranked;
    int pos;
    boolean allGamesEnded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_details_admin);

        myGlob = (MyGlobals) getApplicationContext();

        intentFromTeam = myGlob.getTeamIntent();

        pos = getIntent().getIntExtra("pos", -1);

        tourn = myGlob.getTournAt(pos);

        title = (TextView) (findViewById(R.id.tournName));
        title.setText(tourn.getName() + " Details");

        tournType = (TextView) (findViewById(R.id.tournType));
        tournType.setText(tourn.getType());
    }

//if there are teams then...
    public void viewTeams(View view) {
        if (intentFromTeam != null) {

            Intent teamIntent = new Intent(this, TeamsInTournament.class)
                    .putStringArrayListExtra("teamlist", intentFromTeam.getStringArrayListExtra("team-list"));
            startActivity(teamIntent);

        } else {
            Intent intent = new Intent(this, TeamsInTournament.class);
            startActivity(intent);
        }
    }

    //if the game has ended, the ranked list will not be null
    public void viewRank(View view) {
        //if the tournament has ended
        if(tourn.getTournEnded()) {

            //if this is a round robin, then ranking of teams is done ... else only the winner is shown
            if(tourn.getType().equals("Round Robin")) {

                //get the array of sorted teams
                Team[] sortedTeamsByWins = tourn.determineRanking();
                ArrayList<String> ranked = new ArrayList<>();

                for (int i = 0; i < sortedTeamsByWins.length; i++)
                    ranked.add((i+1) + ". " + sortedTeamsByWins[i].getTeamName());

                Intent Intent = new Intent(getApplicationContext(), TournRankings.class).putStringArrayListExtra("rank",ranked);
                startActivity(Intent);
            }else{
                Team winner = tourn.determineWinner();

                Intent Intent = new Intent(getApplicationContext(), TournRankings.class).putExtra("win", winner.getTeamName());
                startActivity(Intent);
            }
        //if the tournament isnt finished
        }else{
            Toast mytoast = Toast.makeText(getApplicationContext(), "No Rankings to view", Toast.LENGTH_SHORT);
            mytoast.show();
        }
    }

    public void viewGames(View view) {

      //if the tournament has started and the games list is not null, it will take you to the activity to viewGames
        if (tourn.getTournStarted()) {

            ArrayList<String> games =  tourn.getGameList();
            if(games!=null){
                Intent gameIntent = new Intent(this, ViewGames.class)
                        .putExtra("tournPos",pos)
                        .putStringArrayListExtra("gamelist", games)
                        .putExtra("userType","Admin");
                startActivity(gameIntent);

            }
        }
    }

    public void endTournament(View view) {

        //CHECK IF tournament has started
        if (tourn.getTournStarted()) {
            //get the array of games assosiated with the tournament
            Game[] tournGames = tourn.getGameArray();
            allGamesEnded=true;

        //loop through the gamesArray to check if all games details have been set
            for(int i=0; i < tournGames.length; i++){
                if(tournGames[i].getGameDetailsSet() == false){
                    allGamesEnded=false;
                }
            }

            //if all the tourns have ended
            if(tourn.getTournEnded()==false) {
                tourn.setTournEnded(true);

                Toast mytoast = Toast.makeText(getApplicationContext(), "Tournament is now finished.", Toast.LENGTH_SHORT);
                mytoast.show();

            //GET WINNER
               Team[] sortedTeamsByWins = tourn.determineRanking();
               ranked = new ArrayList<>();

                // if this is a round robin tournament - then create a ranking-list and put all the teams in ascending order in here
                if(tourn.getType().equals("Round Robin")) {
                    for (int i = 0; i < sortedTeamsByWins.length; i++) {
                        ranked.add((i + 1) + ". " + sortedTeamsByWins[i].getTeamName());
                        sortedTeamsByWins[i].setRank((i+1));
                    }
                    Intent Intent = new Intent(getApplicationContext(), TournRankings.class).putStringArrayListExtra("rank",ranked);
                    startActivity(Intent);
                }else{

                    Team winner = tourn.determineWinner();

                    Intent Intent = new Intent(getApplicationContext(), TournRankings.class).putExtra("win", winner.getTeamName());
                    startActivity(Intent);
                }

            }else{
                Toast mytoast = Toast.makeText(getApplicationContext(), "Not all tournament games have ended.", Toast.LENGTH_SHORT);
                mytoast.show();
            }
        } else {
            Toast mytoast = Toast.makeText(getApplicationContext(), "Tournament has not started.", Toast.LENGTH_SHORT);
            mytoast.show();
        }
    }

    public void startTournament(View view) {


        //CHECK IF MIN NUMBER OF TEAMS REACHED
        if (myGlob.getNumberOfTeams() >= tourn.getMinNumOfTeams()){
            //CHECK IF ITS AN EVEN NUMBER OF TEAMS OR IF ITS A ROUND ROBIN TOURNAMENT
           if(myGlob.getNumberOfTeams() % 2 == 0 || (tourn.getType().equals("Round Robin"))) {
                //CHECK THAT THE TOURNAMENT DIDNT START AND THAT IT HASNE ENDED EITHER
               if (tourn.getTournEnded() == false && tourn.getTournStarted() == false) {
                   tourn.setTournStarted(true);

                   ArrayList<String> newTeamList = myGlob.getTeamList();
                   Team[] newTeam = myGlob.getTeamArray();
                   tourn.addTeams(newTeam, newTeamList, newTeam.length);

                   //Schedule the game
                   tourn.scheduleGames();
                   ArrayList<String> games = tourn.getGameList();

                  tourn.setGameList(games);
                  myGlob.setGameArray(tourn.getGameArray());

                   Toast mytoast = Toast.makeText(getApplicationContext(), "Tournament has now started.", Toast.LENGTH_SHORT);
                   mytoast.show();

                   Intent intentToGames = new Intent(this, ViewGames.class)
                           .putExtra("tournPos",tourn.getTournIndex())
                           .putExtra("userType", "Admin")
                           .putStringArrayListExtra("gamelist", games);
                   myGlob.setGameIntent(intentToGames);
                   startActivity(intentToGames);
               } else {
                   Toast mytoast = Toast.makeText(getApplicationContext(), "Tournament already started.", Toast.LENGTH_SHORT);
                   mytoast.show();
               }
           }else{
               Toast mytoast = Toast.makeText(getApplicationContext(), "Please enter in an even number of teams", Toast.LENGTH_SHORT);
               mytoast.show();
           }
    }else{
            Toast mytoast = Toast.makeText(getApplicationContext(), "Not enough teams to start !", Toast.LENGTH_SHORT);
            mytoast.show();
        }
}
}
