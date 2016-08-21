package f.lucia.tournamentmakerapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Lucia on 2015-12-03.
 * Allows the team manager to either  enter a team in the tournament or interact with the tournament
 */

public class TournamentOptionsTManage extends ActionBarActivity {

   int posInArray;
    public Tournament tourn;
    MyGlobals myGlob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_options_tmanage);

        myGlob = (MyGlobals)getApplicationContext();


        posInArray = getIntent().getIntExtra("pos",-1);
        tourn = myGlob.getTournAt(posInArray);
    }


    public void onSubmitTeam(View view){
        if(tourn.getMaxNumOfTeams() != myGlob.getNumberOfTeams()) {
            Intent intent = new Intent(TournamentOptionsTManage.this, TeamSubmission.class).putExtra("pos", posInArray);
            startActivity(intent);
        }else{
            Toast mytoast = Toast.makeText(getApplicationContext(), "Max Teams reached for tournament", Toast.LENGTH_LONG);
            mytoast.show();
        }
    }


    public void onViewTourn(View view){
        Intent intent = new Intent(TournamentOptionsTManage.this, TournamentDetailsActivityTManage.class)
                .putExtra("pos",posInArray);
        startActivity(intent);
    }

}
