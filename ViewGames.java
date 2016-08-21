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
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Lucia and Tayo on 2015-12-02.
 * Allows the user to view games in the tournament
 */
public class ViewGames extends ActionBarActivity {

    String user;
    ArrayList<String> gameList;
    MyGlobals myGlob;
    int tournPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_games);

        myGlob = (MyGlobals) getApplicationContext();

        gameList = getIntent().getStringArrayListExtra("gamelist");

       tournPos = getIntent().getIntExtra("tournPos",-1);


         user = getIntent().getStringExtra("userType");
        if (gameList!= null) {

        //get the tournament at this position
            Tournament tourn = myGlob.getTournAt(tournPos);
            gameList = tourn.getGameList();

            ListView gView = (ListView) findViewById(R.id.gameView);
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, gameList);
            gView.setAdapter(adapter);


            gView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                //if this is an admin, go to the admin view page else go user view page
                    if(user.equals("Admin")) {

                        Intent intent = new Intent(ViewGames.this, AdminGameView.class)
                             .putExtra("tPos",tournPos)
                                .putExtra("pos", position);
                        startActivity(intent);
                        finish();
                    }else if(user.equals("Other")){
                        Intent intent = new Intent(ViewGames.this, UserGameView.class).putExtra("pos", position);
                        startActivity(intent);
                    }

                }
            });

        }else{ Toast mytoast = Toast.makeText(getApplicationContext(), "gamelist=nulll", Toast.LENGTH_SHORT);
            mytoast.show();}

    }

}
