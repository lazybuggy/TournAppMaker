package f.lucia.tournamentmakerapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Lucia on 2015-11-30.
 * Allows the team manager to view tournaments and add teams to the tournament
 */
public class TManagerActivity extends ActionBarActivity {

    ArrayList<String> tournList;
    MyGlobals myGlob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tmanager);

        myGlob = (MyGlobals)getApplicationContext();

            tournList = getIntent().getStringArrayListExtra("tournlist");

        //if a tournament exists, this will display a list of current tournaments
        if (tournList != null){
            ListView myList = (ListView) findViewById(R.id.listView2);
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, tournList);
            myList.setAdapter(adapter);

            myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                    Intent intent = new Intent(TManagerActivity.this,TournamentOptionsTManage.class).putExtra("pos",position);
                    startActivity(intent);

                }
            });
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tmanager, menu);
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

//view all teams
    public void viewTeams(View view){

        Intent intentFromTeam = myGlob.getTeamIntent();
        if(intentFromTeam !=null) {

            Intent teamIntent =new Intent(this, ViewTeams.class).putStringArrayListExtra("teamlist", intentFromTeam.getStringArrayListExtra("team-list"));

            startActivity(teamIntent);

        }else{

            Intent intent = new Intent(TManagerActivity.this, ViewTeams.class);
            startActivity(intent);
        }

    }

    public void SignOut(View view){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
    public void goToHelp(View view)
    {
        Intent helpIntent = new Intent(getApplicationContext(), ActivityHelp.class);
        startActivity(helpIntent);

    }

}

