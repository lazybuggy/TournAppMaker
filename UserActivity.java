package f.lucia.tournamentmakerapp;

import android.support.v7.app.ActionBarActivity;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Created by Lucia on 2015-11-30.
 * Allows the user to view tournaments
 */

public class UserActivity extends ActionBarActivity {


    ArrayList<String> tournList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);


        tournList = getIntent().getStringArrayListExtra("tournlist");


        //if a tournament-list exists
        if (tournList != null) {
            ListView myList = (ListView) findViewById(R.id.tournView);
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, tournList);
            myList.setAdapter(adapter);


            myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                    Intent intent = new Intent(UserActivity.this, TournamentDetailsActivityTManage.class).putExtra("pos", position);
                    startActivity(intent);

                }
            });

        }
    }

    public void onSignOutClick(View view){
       /// showProgress(true);
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
    public void goToHelp(View view)
    {
        Intent helpIntent = new Intent(getApplicationContext(), ActivityHelp.class);//send to activity help page
        startActivity(helpIntent);

    }


}
