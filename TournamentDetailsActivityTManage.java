package f.lucia.tournamentmakerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
/**
 * Created by Lucia on 2015-11-30.
 * Allows the admin to interact with the tournament
 */

public class TournamentDetailsActivityTManage extends ActionBarActivity {


    TextView title;
    TextView tournType;
    MyGlobals myGlob;
    Intent intentFromTeam;
    Intent intentGame;
    int pos;
    Tournament tourn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_details_tmanage);

        myGlob = (MyGlobals)getApplicationContext();

        pos= getIntent().getIntExtra("pos",-1);
         tourn = myGlob.getTournAt(pos);

        title =  (TextView)(findViewById(R.id.tnDetails));
        title.setText(tourn.getName()+" Details");

        tournType = (TextView)(findViewById(R.id.tournType));
        tournType.setText(tourn.getType());

        intentFromTeam = myGlob.getTeamIntent();
        intentGame = myGlob.getGameIntent();

    }

    public void onRankClick(View view)
    {
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
        //if the tournament hasnt finished
        }else{
            Toast mytoast = Toast.makeText(getApplicationContext(), "No Rankings to view", Toast.LENGTH_SHORT);
            mytoast.show();
        }
    }

    //if games have been created
    public void onGamesClick(View view){
        if(tourn.getTournStarted()){
            if(intentGame !=null) {

                    ArrayList<String>  tourns = tourn.getGameList();
                Intent teamIntent =new Intent(this, ViewGames.class)
                       .putExtra("userType","Other")
                        .putExtra("tournPos",pos)
                        .putStringArrayListExtra("gamelist", tourns);
                startActivity(teamIntent);

            }
        }
    }

    public void onTeamClick(View view){

        if(intentFromTeam !=null) {

            Intent teamIntent =new Intent(this, TeamsInTournament.class)
                    .putStringArrayListExtra("teamlist", intentFromTeam.getStringArrayListExtra("team-list"));
            startActivity(teamIntent);

        }else{
            Intent intent = new Intent(this, TeamsInTournament.class);
            startActivity(intent);
        }
    }
}
