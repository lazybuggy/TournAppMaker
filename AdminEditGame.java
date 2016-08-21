package f.lucia.tournamentmakerapp;

/**
 * Created by Lucia and Tayo and Nel on 2015-12-04.
 * This page allows the admin to enter in the scores of the game
 */

import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdminEditGame extends AppCompatActivity {


    MyGlobals myGlob;
    Team[] teamsInGame;


    public  EditText team1PointsScored;
    public  EditText team1Offsides;
    public  EditText team1Fouls;
    public EditText team1Possesion;
    public  EditText team2PointsScored;
    public  EditText team2Offsides;
    public  EditText team2Fouls;
    public EditText team2Possesion;

    Game currGame;
    int position;
    int tournPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_game);

        //initalize myGlobals
        myGlob = (MyGlobals) getApplicationContext();

        //position in the game list
       position = getIntent().getIntExtra("position", -1);
        //position in the tournament list
        tournPos = getIntent().getIntExtra("tPos",-1);

        //call the array of games for the tournament
        Tournament tourn = myGlob.getTournAt(tournPos);
        Game[] tournGames = tourn.getGameArray();

        //get the game at this position in the game list
        currGame = tournGames[position];
        teamsInGame = currGame.getTeamsInGame();

        //set team names in the activity
       TextView team1 = (TextView) findViewById(R.id.team1);
        team1.setText(teamsInGame[0].getTeamName());

        TextView team2 = (TextView) findViewById(R.id.team2);
        team2.setText(teamsInGame[1].getTeamName());


        team1PointsScored = (EditText) findViewById(R.id.Team1Points);
        team1Offsides = (EditText) findViewById(R.id.Team1Offsides);
        team1Fouls = (EditText) findViewById(R.id.Team1Fouls);
        team1Possesion= (EditText) findViewById(R.id.Team1Possesion);
        team2PointsScored= (EditText) findViewById(R.id.Team2Points);
        team2Offsides= (EditText) findViewById(R.id.Team2Offsides);
        team2Fouls= (EditText) findViewById(R.id.Team2Fouls);
        team2Possesion= (EditText) findViewById(R.id.Team2Possesion);


    }


    public void savebuttonOnClick(View v) {

        boolean cancel=false;
        View focusView=null;

        //get the information stored in the EditText on the activity
        String teamPoints = ((EditText) findViewById(R.id.Team1Points)).getText().toString();
        String teamPoints2 = ((EditText) findViewById(R.id.Team2Points)).getText().toString();

        String teamOffsides = ((EditText) findViewById(R.id.Team1Offsides)).getText().toString();
        String teamOffsides2 = ((EditText) findViewById(R.id.Team2Offsides)).getText().toString();

        String teamFouls = ((EditText) findViewById(R.id.Team1Fouls)).getText().toString();
        String teamFouls2 = ((EditText) findViewById(R.id.Team2Fouls)).getText().toString();


        String teamPossesions = ((EditText) findViewById(R.id.Team1Possesion)).getText().toString();
        String teamPossesions2 = ((EditText) findViewById(R.id.Team2Possesion)).getText().toString();


    // Check if a number is stored in the edittext fields
        if (TextUtils.isEmpty(teamPoints)) {
            team1PointsScored.setError(getString(R.string.error_field_required));
            focusView = team1PointsScored;
            cancel = true;
        }
        else {
            try {
                int t = Integer.parseInt(teamPoints);
            } catch (Exception e) {
                team1PointsScored.setError(getString(R.string.invalid_character));
                focusView = team1PointsScored;
                cancel = true;
            }
        }
        if (TextUtils.isEmpty(teamOffsides)){
            team1Offsides.setError(getString(R.string.error_field_required));
            focusView = team1Offsides;
            cancel = true;
        }
        else{
            try{
                int t = Integer.parseInt(teamOffsides);
            }
            catch(Exception e){
                team1Offsides.setError(getString(R.string.invalid_character));
                focusView = team1Offsides;
                cancel = true;
            }
        }if (TextUtils.isEmpty(teamFouls)) {
            team1Fouls.setError(getString(R.string.error_field_required));
            focusView = team1Fouls;
            cancel = true;
        }
        else{
            try{
                int t = Integer.parseInt(teamFouls);
            }
            catch(Exception e){
                team1Fouls.setError(getString(R.string.invalid_character));
                focusView = team1Fouls;
                cancel = true;
            }

        }
        if (TextUtils.isEmpty(teamPossesions)) {
            team1Possesion.setError(getString(R.string.error_field_required));
            focusView = team1Possesion;
            cancel = true;

        }
        else{
            try{
                int t = Integer.parseInt(teamPossesions);
            }
            catch(Exception e){
                team1Possesion.setError(getString(R.string.invalid_character));
                focusView = team1Possesion;
                cancel = true;
            }

        }

        if (TextUtils.isEmpty(teamPoints2)) {
            team2PointsScored.setError(getString(R.string.error_field_required));
            focusView = team2PointsScored;
            cancel = true;
        }
        else{
            try{
                int t = Integer.parseInt(teamPoints2);
            }
            catch(Exception e){
                team2PointsScored.setError(getString(R.string.invalid_character));
                focusView = team2PointsScored;
                cancel = true;
            }

        }

        if (TextUtils.isEmpty(teamFouls2)) {
            team2Fouls.setError(getString(R.string.error_field_required));
            focusView = team2Fouls;
            cancel = true;
        }
        else{
            try{
                int t = Integer.parseInt(teamFouls2);
            }
            catch(Exception e){
                team2Fouls.setError(getString(R.string.invalid_character));
                focusView = team2Fouls;
                cancel = true;
            }

        }
        if (TextUtils.isEmpty(teamOffsides2)) {
            team2Offsides.setError(getString(R.string.error_field_required));
            focusView = team2Offsides;
            cancel = true;
        }
        else{
            try{
                int t = Integer.parseInt(teamOffsides2);
            }
            catch(Exception e){
                team2Offsides.setError(getString(R.string.invalid_character));
                focusView = team2Offsides;
                cancel = true;
            }

        }
        if (TextUtils.isEmpty(teamPossesions2)) {
            team2Possesion.setError(getString(R.string.error_field_required));
            focusView = team2Possesion;
            cancel = true;
        }
        else{
            try{
                int t = Integer.parseInt(teamPossesions2);
            }
            catch(Exception e){
                team2Possesion.setError(getString(R.string.invalid_character));
                focusView = team2Possesion;
                cancel = true;
            }

        }

        if(cancel)
        {
            focusView.requestFocus();
        }
        else
        {
            //the details for the game has been set, thus it cannot be set again
            currGame.setGameDetailsSet(true);

            //set the goals from each team
            teamsInGame[0].setGoals(Integer.parseInt(teamPoints));
            teamsInGame[1].setGoals(Integer.parseInt(teamPoints2));

            currGame.setGameGoals(teamsInGame[0],Integer.parseInt(teamPoints));
            currGame.setGameGoals( teamsInGame[1],Integer.parseInt(teamPoints2));

            teamsInGame[0].setTeamOffsides(Integer.parseInt(teamOffsides));
            teamsInGame[1].setTeamOffsides(Integer.parseInt(teamOffsides2));

            currGame.setGameOffsides(teamsInGame[0],Integer.parseInt(teamOffsides));
            currGame.setGameOffsides( teamsInGame[1],Integer.parseInt(teamOffsides2));

             teamsInGame[0].setTeamFouls(Integer.parseInt(teamFouls));
            teamsInGame[1].setTeamFouls(Integer.parseInt(teamFouls2));

            currGame.setGameFouls(teamsInGame[0],Integer.parseInt(teamFouls));
            currGame.setGameFouls( teamsInGame[1],Integer.parseInt(teamFouls2));

            teamsInGame[0].setTeamPoss(Integer.parseInt(teamPossesions));
            teamsInGame[1].setTeamPoss(Integer.parseInt(teamPossesions2));

            currGame.setGamePoss(teamsInGame[0],Integer.parseInt(teamPossesions));
            currGame.setGamePoss( teamsInGame[1],Integer.parseInt(teamPossesions));

            // get the current tournament
           Tournament tourn = myGlob.getTournAt(tournPos);

            //If this tournament is a KnockOut tournament
            if (tourn.getType().equals("Knock Out")){

                //eliminate the loser
                tourn.elimination(currGame);

                //if the game has been played for that round decrement
                    //FOR EXAMPLE: if there were 4 teams, there would be 2 original games
                    //and it would decrement each time these 2 games are played
                myGlob.setNumbGamesPlayed(myGlob.getNumbGamesPlayed() - 1);

            //check if all ORIGINAL games have been played
            if (myGlob.getNumbGamesPlayed() == 0) {

                //schedule the new games to be played
                tourn.scheduleGames();

                ArrayList<String> games = tourn.getGameList();

                //set the game list to display these new games and change the game array
                tourn.setGameList(games);
                myGlob.setGameArray(tourn.getGameArray());

                Intent intent = new Intent(AdminEditGame.this, ViewGames.class)
                        .putExtra("pos", position)
                        .putExtra("tournPos",tournPos)
                        .putExtra("userType","Admin")
                        .putStringArrayListExtra("gamelist", games);
                startActivity(intent);
                finish();
            }
            //if the ORIGINAL games havent been played
            else{
                Intent intent = new Intent(AdminEditGame.this, AdminGameView.class)
                        .putExtra("pos", position)
                        .putExtra("tPos",tournPos);
                startActivity(intent);
                finish();

            }
        }
            //if this is not a KNOCKOUT tournament
            else {

                Intent intent = new Intent(AdminEditGame.this, AdminGameView.class)
                        .putExtra("pos", position)
                        .putExtra("tPos",tournPos);
                startActivity(intent);
                finish();
            }
        }
    }


    public void cancelbuttonOnClick(View v) {
        finish();
    }


}

