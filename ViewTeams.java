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
 * Created by Lucia and Leila on 2015-12-02.
 * Allows the user to view teams in the tournament
 */

public class ViewTeams extends ActionBarActivity {

    MyGlobals myGlob;
    ArrayList<String> teamList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_teams);

        myGlob = (MyGlobals)getApplicationContext();

        teamList = getIntent().getStringArrayListExtra("teamlist");

        if (teamList!= null) {
            ListView myList = (ListView) findViewById(R.id.teamView);
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, teamList);
            myList.setAdapter(adapter);


            myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                    Intent intent = new Intent(ViewTeams.this, TeamDetails.class).putExtra("pos",position);
                    startActivity(intent);

                }
            });

        }
    }

}
