package f.lucia.tournamentmakerapp;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
/**
 * Created by Lucia and Nel on 2015-11-30.
 * Activity & code that takes care of the addition of a tournament
 */

public class newTournament extends ActionBarActivity {

    static MyGlobals myGlob;

    static int currentTourns=0;
    static  int currentArraySize = 5;
    static String[] onGoingTourns = new String[currentArraySize];
    static Tournament[] currTourns = new Tournament[currentArraySize];

    public Tournament tourn;

    String trnType;
    int minTeam;
    int maxTeam;

    //declare variables to hold xml file information
    private EditText tournName;
    private RadioGroup minTeams;
    private RadioGroup maxTeams;
    private RadioGroup tournType;
    private RadioButton minTeamChosen;
    private RadioButton maxTeamChosen;
    private RadioButton tournTypeChosen;
    private TextView minTextView;
    private TextView maxTextView;
    private TextView typeTextView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_tournament);

        myGlob = (MyGlobals)getApplicationContext();

        tournName= (EditText) findViewById(R.id.editTournName);
        minTeams= (RadioGroup) findViewById(R.id.MinTeamGroup);
        maxTeams= (RadioGroup) findViewById(R.id.MaxTeamGroup);
        tournType=(RadioGroup) findViewById(R.id.tournTypeGroup);
        minTextView= (TextView)findViewById(R.id.min);
        maxTextView =(TextView)findViewById(R.id.max);
        typeTextView=(TextView) findViewById(R.id.tournType);

    }




    public void onCancel(View view){
        myGlob.setTournCreated(false);

        Intent intent = new Intent(this, AdminActivity.class);
        startActivity(intent);
        finish();
    }

    //This button creates a new tournament with the information given in the activity
    public void onNext(View view){


        boolean cancel=false;
        View focusView = null;
        String name=tournName.getText().toString();

        minTeamChosen= (RadioButton)findViewById(minTeams.getCheckedRadioButtonId());
        maxTeamChosen=(RadioButton)findViewById(maxTeams.getCheckedRadioButtonId());
        tournTypeChosen=(RadioButton)findViewById(tournType.getCheckedRadioButtonId());

        minTextView.setError(null);
        maxTextView.setError(null);
        typeTextView.setError(null);

        if(TextUtils.isEmpty(name))
        {
            tournName.setError(getString(R.string.error_field_required));
            focusView = tournName;
            cancel = true;
        }

        if(minTeamChosen==null)
        {
            minTextView.setError(getString(R.string.error_field_required));
            focusView = minTextView;
            cancel = true;

        }

        if(maxTeamChosen==null)
        {
            maxTextView.setError(getString(R.string.error_field_required));
            focusView = maxTextView;
            cancel = true;
        }

        if(tournTypeChosen==null)
        {
            typeTextView.setError(getString(R.string.error_field_required));
            focusView=typeTextView;
            cancel=true;
        }else if(((tournTypeChosen.getText()).toString()).equals("Combination")){
            cancel=true;
        }

        if (cancel)
        {
            // There was an error;
            // form field with an error.
            if(((tournTypeChosen.getText()).toString()).equals("Combination")) {
                focusView=typeTextView;
                Toast mytoast = Toast.makeText(getApplicationContext(), "Purchase required for use of this tournament.", Toast.LENGTH_LONG);
                mytoast.show();
            }
            focusView.requestFocus();

        }
        //if  nothing is wrong with the input from the acitivity
        else {


            minTeam = Integer.parseInt(minTeamChosen.getText().toString());
            maxTeam = Integer.parseInt(maxTeamChosen.getText().toString());
            trnType = tournTypeChosen.getText().toString();

            //creates a new knockout tourn
            if(trnType.equals("Knockout")) {
                tourn = new KnockOut(maxTeam, minTeam, name);
            }
            //creates a new round robin tourn
            else if(trnType.equals("Round Robin")){
                tourn=new RoundRobin(maxTeam, minTeam, name);
            }
            //creates a new combination tourn
            else if(trnType.equals("Combination")){
                tourn = new Combination(maxTeam,minTeam,name);
            }

            //set the details for the tournament
            tourn.setName(name);
            tourn.setMaxNumOfTeams(maxTeam);
            tourn.setMinNumOfTeams(minTeam);

            //call the method ADDTOLIST
            ArrayList<String> tournList = addToList(name, tourn);

            String added = "Tournament added!!";

            Toast.makeText(getApplicationContext(), added,Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, AdminActivity.class)
                    .putExtra("pos", tourn.getTournIndex())
                    .putStringArrayListExtra("tourn-list", tournList);
            startActivity(intent);
            finish();
        }



    }


    public static ArrayList<String> addToList(String newTourn, Tournament tourny) {


        if (currentTourns == currentArraySize) {
            //dynamically change array size
            //increment currentarraysize by 5 THEN ADD element
            String[] newOngoingTourns = new String[onGoingTourns.length + 5];
            Tournament[] newCurrTourns = new Tournament[currTourns.length + 5];

            for (int i = 0; i < currentTourns; i++) {
                newOngoingTourns[i] = onGoingTourns[i];
                newCurrTourns[i] = currTourns[i];
            }

            onGoingTourns = newOngoingTourns;
            currTourns = newCurrTourns;

            currentArraySize = currentArraySize + 5;

            onGoingTourns[currentTourns] = newTourn;
            currTourns[currentTourns] = tourny;
            currentTourns++;

        }
        //If the tournament array is not at its max-capacity
        else {
            onGoingTourns[currentTourns] = newTourn;
            currTourns[currentTourns] = tourny;
            currentTourns++;
        }


        final ArrayList<String> list = new ArrayList<String>();

        //Converting Array to ArrayList
        for (int i = 0; i < currentTourns; i++) {
            list.add(onGoingTourns[i]);
        }

        Intent TournIntent = new Intent()
                .putExtra("tournsName",newTourn)
                .putStringArrayListExtra("tourn-list", (ArrayList<String>)list);


        //create a new array to set only the number of tourns (Do not need null values)
        Tournament[] newTourns = new Tournament[currentTourns];

        for(int i=0; i < newTourns.length;i++){
            newTourns[i]= currTourns[i];
        }

        myGlob.setTournIntent(TournIntent);
        myGlob.setTournArray(newTourns);

        return list;
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
