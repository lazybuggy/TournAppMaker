package f.lucia.tournamentmakerapp;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.app.Activity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
/**
 * Created by Leila and Lucia on 2015-11-30.
 * Allows the user to submit a team
 */

    public class TeamSubmission extends ActionBarActivity {

        public Team theTeam;

        static  int currentArraySize = 5;
        static int currentTeams=0;

        static String[] onGoingTeams = new String[currentArraySize];
        static Team[] currTeams = new Team[currentArraySize];


        static MyGlobals myGlob;
        private EditText tName;
        private EditText cName;
        private EditText nPlayers;
        private EditText cOrigin;
        private EditText tBio;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_team_submission);

            tName=((EditText) findViewById(R.id.editText01));
            cName=((EditText) findViewById(R.id.editText02));
            nPlayers=((EditText) findViewById(R.id.editText03));
            cOrigin = ((EditText) findViewById(R.id.editText04));
            tBio = ((EditText) findViewById(R.id.editText05));

            myGlob = (MyGlobals)getApplicationContext();

        }


        public void btncancelClick (View view) {
            Toast mytoast = Toast.makeText(getApplicationContext(), "Submission cancelled", Toast.LENGTH_LONG);
            mytoast.show();
            finish();
        }



        public void btnsubmitClick (View view){

            boolean cancel=false;
            View focusView=null;


            String teamName = tName.getText().toString();
            String coachName =cName.getText().toString();
            String nPlay = nPlayers.getText().toString();
            String cityOrigin =cOrigin.getText().toString();
            String bio =tBio.getText().toString();

            //this checks if all the EditTexts are filled in
            if(TextUtils.isEmpty(teamName))
            {
                tName.setError(getString(R.string.error_field_required));
                focusView=tName;
                cancel=true;
            }
            if(TextUtils.isEmpty(coachName))
            {
                cName.setError(getString(R.string.error_field_required));
                focusView=cName;
                cancel=true;
            }
            if(TextUtils.isEmpty(nPlay))
            {
                nPlayers.setError(getString(R.string.error_field_required));
                focusView=nPlayers;
                cancel=true;
            }
            if(TextUtils.isEmpty(cityOrigin))
            {
                cOrigin.setError(getString(R.string.error_field_required));
                focusView=cOrigin;
                cancel=true;
            }
            if(TextUtils.isEmpty(bio))
            {
                tBio.setError(getString(R.string.error_field_required));
                focusView=tBio;
                cancel=true;
            }


            if(cancel)
            {
                focusView.requestFocus();
            }
            //if all of them are filled in correctly
            else {
                theTeam= new Team();
                theTeam.setTeamName(teamName);
                theTeam.setCoachName(coachName);
                theTeam.setNPlayers((nPlay));
                theTeam.setCityOfOrigin(cityOrigin);
                theTeam.setBiography(bio);


                Toast mytoast = Toast.makeText(getApplicationContext(), "Submission completed", Toast.LENGTH_LONG);
                mytoast.show();

                //THIS SHOULD NOW ADD TO THE TEAMS LIST VIEW...........
                String tn = "Team name";

                //call the method to add the team
                ArrayList<String> teamList = addToTeamList(theTeam, teamName);

                String added = "Team added !";
                myGlob.setNumberOfTeams(theTeam.getTeamsInArray());

                Toast.makeText(getApplicationContext(), added, Toast.LENGTH_SHORT).show();

                startActivity(new Intent(TeamSubmission.this, TeamDetails.class)
                        .putExtra("pos", theTeam.getAmountOfTeams())
                        .putStringArrayListExtra("team-list", teamList));
                finish();
            }
        }

    public static ArrayList<String> addToTeamList(Team team, String tName){

        if (currentTeams == currentArraySize) {
            //dynamically change array size
            //increment currentarraysize by 5 THEN ADD element
            String[] newOngoingTeams = new String[onGoingTeams.length + 5];
            Team[] newCurrTeams = new Team[currTeams.length + 5];

            for (int i = 0; i < currentTeams; i++) {
                newOngoingTeams[i] = onGoingTeams[i];
                newCurrTeams[i] = currTeams[i];
            }

            onGoingTeams = newOngoingTeams;
            currTeams = newCurrTeams;
            currentArraySize = currentArraySize + 5;

            onGoingTeams[currentTeams] = tName;
            currTeams[currentTeams]= team;
            currentTeams++;

        } else {
            onGoingTeams[currentTeams] = tName;
            currTeams[currentTeams]= team;
            currentTeams++;
        }

        final ArrayList<String> list = new ArrayList<String>();

        //Converting Array to ArrayList
        for (int i = 0; i < currentTeams; i++) {
            list.add(onGoingTeams[i]);
        }

        Intent TeamIntent = new Intent()
                .putExtra("teamName",team.getTeamName())
                .putStringArrayListExtra("team-list", list);

        myGlob.setTeamIntent(TeamIntent);

        //this put the Team array to the proper Team size
        Team[] newTeams = new Team[currentTeams];

        for(int i=0; i < newTeams.length;i++){
            newTeams[i]= currTeams[i];
        }

        myGlob.setTeamArray(newTeams);
        myGlob.setTeamList(list);

        return list;


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_team_submission, menu);
        return true;
    }


}
