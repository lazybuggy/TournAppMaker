package f.lucia.tournamentmakerapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.widget.Toast.makeText;
/**
 * Created by Lucia on 2015-11-30.
 * This page displays the tournaments currently in the app or allows the admin to add a tournament
 */

public class AdminActivity extends ActionBarActivity {


    ArrayAdapter<String> adapter;
    MyGlobals myGlob;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        //initalize myGlobals
        myGlob = (MyGlobals)getApplicationContext();

        //get the list of tournaments sent from the login-page
        ArrayList<String> list = getIntent().getStringArrayListExtra("tourn-list");


        listView=(ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);

        //if the list is not null set the adapter
        if(list != null)
            listView.setAdapter(adapter);

        //set the on-click listener for each tournament in the list
        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                Intent intent = new Intent(AdminActivity.this, TournamentDetailsActivityAdmin.class)
                        .putExtra("pos",position);
                startActivity(intent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_admin, menu);
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

    //go to the tournament submission page
    public void OnAddTournamentButton(View view) {
        Intent intent = new Intent(AdminActivity.this, newTournament.class);
        startActivity(intent);
        finish();

    }

    //sign out of the admin
    public void onSignOut(View view){
        Intent intent = new Intent(AdminActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void goToHelp(View view)
    {
        Intent helpIntent = new Intent(getApplicationContext(),ActivityHelp.class);//send to help page
        startActivity(helpIntent);

    }



}
