package f.lucia.tournamentmakerapp;

/**
 * Created by Lucia on 2015-11-30.
 * This page allows the admin to view the details of the individual game
 */

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;


import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdminGameView extends ActionBarActivity {


    Game[] tournGames;

    MyGlobals myGlob;
    int position;
    int tPos;
    Game currGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin_game_view);

        myGlob = (MyGlobals) getApplicationContext();


      // the position of the current game in the list
        position = getIntent().getIntExtra("pos", -1);
      //the position of the current tournament in the list
        tPos = getIntent().getIntExtra("tPos", -1);

        //get the tournament at this position and the games within the tournament
        Tournament tourn = myGlob.getTournAt(tPos);
        tournGames = tourn.getGameArray();

        //find the current game in the game-array and get the teams within the game
        currGame = tournGames[position];
        Team[] thisTeam = currGame.getTeamsInGame();


        //set all the information for the teams
        TextView team1 = (TextView) findViewById(R.id.team1);
        team1.setText(thisTeam[0].getTeamName());

        TextView team2 = (TextView) findViewById(R.id.textView11);
        team2.setText(thisTeam[1].getTeamName());

        TextView tS1 = (TextView) findViewById(R.id.name_team_1_points_scored);
        tS1.setText(Integer.toString(currGame.getGameGoals(thisTeam[0])));

        TextView tO1 = (TextView) findViewById(R.id.name_team_1_offsides);
        tO1.setText(Integer.toString(currGame.getGameOffsides(thisTeam[0])));

        TextView tF1 = (TextView) findViewById(R.id.name_team_1_fouls);
        tF1.setText(Integer.toString(currGame.getGameFouls(thisTeam[0])));

        TextView tP1 = (TextView) findViewById(R.id.name_team_1_possesion);
        tP1.setText(Integer.toString(currGame.getGamePoss(thisTeam[0])));


        TextView tS2 = (TextView) findViewById(R.id.name_team_2_points_scored);
        tS2.setText(Integer.toString(currGame.getGameGoals(thisTeam[1])));

        TextView tO2 = (TextView) findViewById(R.id.name_team_2_offsides);
        tO2.setText(Integer.toString(currGame.getGameOffsides(thisTeam[1])));

        TextView tF2 = (TextView) findViewById(R.id.name_team_2_fouls);
        tF2.setText(Integer.toString(currGame.getGameFouls(thisTeam[1])));

        TextView tP2 = (TextView) findViewById(R.id.name_team_2_possesion);
        tP2.setText(Integer.toString(currGame.getGamePoss(thisTeam[1])));

   }
    public void onEdit(View v) {

        //if the game details havent been set yet  then allow edits
       if(currGame.getGameDetailsSet() == false) {
           Intent intent = new Intent(AdminGameView.this, AdminEditGame.class)
                   .putExtra("tPos",tPos)
                   .putExtra("position", position);
           startActivity(intent);
           finish();
       }else{
           Toast mytoast = Toast.makeText(getApplicationContext(), "Game Details already set.", Toast.LENGTH_LONG);
           mytoast.show();
       }
    }
}
