package f.lucia.tournamentmakerapp;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
/**
 * Created by Lucia and Tayo on 2015-11-30.
 * Allows the user/team manager to view games
 */
public class UserGameView extends AppCompatActivity {


    MyGlobals myGlob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_game_view);

        myGlob = (MyGlobals) getApplicationContext();

        Game[] tournGames = myGlob.getGameArray();
        int position = getIntent().getIntExtra("pos", -1);

        Game currGame = tournGames[position];
        Team[] thisTeam = currGame.getTeamsInGame();


        TextView team1 = (TextView) findViewById(R.id.team1);
        team1.setText(thisTeam[0].getTeamName());

        TextView team2 = (TextView) findViewById(R.id.textView11);
        team2.setText(thisTeam[1].getTeamName());

        TextView tS1 = (TextView) findViewById(R.id.Team1Points);
        tS1.setText(Integer.toString(currGame.getGameGoals(thisTeam[0])));

        TextView tO1= (TextView) findViewById(R.id.Team1Offsides);
        tO1.setText(Integer.toString(currGame.getGameOffsides(thisTeam[0])));

        TextView tF1 = (TextView) findViewById(R.id.Team1Fouls);
        tF1.setText(Integer.toString(currGame.getGameFouls(thisTeam[0])));

        TextView tP1 = (TextView) findViewById(R.id.Team1Possesion);
        tP1.setText(Integer.toString(currGame.getGamePoss(thisTeam[0])));


        TextView tS2 = (TextView) findViewById(R.id.Team2Points);
        tS2.setText(Integer.toString(currGame.getGameGoals(thisTeam[1])));

        TextView tO2= (TextView) findViewById(R.id.Team2Offsides);
        tO2.setText(Integer.toString(currGame.getGameOffsides(thisTeam[1])));

        TextView tF2 = (TextView) findViewById(R.id.Team2Fouls);
        tF2.setText(Integer.toString(currGame.getGameFouls(thisTeam[1])));

        TextView tP2 = (TextView) findViewById(R.id.Team2Possesion);
        tP2.setText(Integer.toString(currGame.getGamePoss(thisTeam[1])));
    }

}
