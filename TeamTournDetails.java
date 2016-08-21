package f.lucia.tournamentmakerapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Leila and Lucia on 2015-12-07.
 * Allows the user to view the details of the tournament
 */
public class TeamTournDetails extends ActionBarActivity {


    MyGlobals myGlob;
    int thisPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_tourn_details);

        myGlob = (MyGlobals)getApplicationContext();

       thisPosition = getIntent().getIntExtra("teamNumber",-1);
       Team thisTeam = myGlob.getTeamAt(thisPosition);

        TextView tName = (TextView) findViewById(R.id.textView01);
        tName.setText(thisTeam.getTeamName());

        TextView cName = (TextView) findViewById(R.id.textView4);
        cName.setText(thisTeam.getCoachName());

        TextView nplayers = (TextView) findViewById(R.id.textView6);
        nplayers.setText(thisTeam.getNPlayers());

      TextView nScore = (TextView) findViewById(R.id.textView8);
       nScore.setText(Integer.toString(thisTeam.getGoals()));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_team_tourn_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //provides further team details
    public void onDetailsClick(View view){
        Intent intent = new Intent(this, TeamDetails.class).putExtra("pos",thisPosition);
        startActivity(intent);
    }
}
