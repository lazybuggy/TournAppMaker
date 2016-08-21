package f.lucia.tournamentmakerapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Lucia on 2015-12-02.
 * Activity for displaying the teams in a tournament
 */

public class TeamsInTournament extends ActionBarActivity {

    ArrayList<String> teamList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams_in_tournament);


        teamList = getIntent().getStringArrayListExtra("teamlist");

        //if a team has been added, then team list is not empty
       // so when a team from this list is clicked the details for the team in the tournament will show
        if (teamList != null){
            ListView myTeams = (ListView) findViewById(R.id.teamView);
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, teamList);
            myTeams.setAdapter(adapter);

            myTeams.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                    Intent intent = new Intent(TeamsInTournament.this,TeamTournDetails.class).putExtra("teamNumber", position);
                    startActivity(intent);
                }
            });
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_teams_in_tournament, menu);
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
