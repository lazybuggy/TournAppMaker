package f.lucia.tournamentmakerapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Leila and Nel on 2015-12-01.
 * Displays the details for each team
 */

public class TeamDetails extends ActionBarActivity {


    MyGlobals myGlob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_details);

        myGlob = (MyGlobals)getApplicationContext();

        //get the int position from the intent
        int thisPosition = getIntent().getIntExtra("pos",-1);
        //call the team at this position of the TeamList
        Team thisTeam = myGlob.getTeamAt(thisPosition);

    //and set all the info about that team to the ACTIVITES textViews
        TextView tName = (TextView) findViewById(R.id.tName);
        tName.setText(thisTeam.getTeamName());

        TextView cName = (TextView) findViewById(R.id.cName);
        cName.setText(thisTeam.getCoachName());

        TextView nplayers = (TextView) findViewById(R.id.nplayers);
        nplayers.setText(thisTeam.getNPlayers());

        TextView origin = (TextView) findViewById(R.id.city);
        origin.setText(thisTeam.getCityOfOrigin());

        TextView bio = (TextView) findViewById(R.id.bio);
        bio.setText(thisTeam.getBiography());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_team_details, menu);
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
}
