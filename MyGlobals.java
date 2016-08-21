package f.lucia.tournamentmakerapp;

/**
 * Created by Lucia on 2015-11-30.
 * Added to by Nel
 *
 * My Globals serves as a Global class storing static information used by all classes within the app
 */
    import android.app.Application;
    import android.content.Context;
    import android.content.Intent;
    import android.net.ConnectivityManager;
    import android.net.NetworkInfo;

    import java.util.ArrayList;

public class MyGlobals extends Application{

     static Intent tournIntent=null;
     static Intent teamIntent=null;
     static Intent gameIntent=null;

    static boolean tournCreated=false;
    static int numberOfTeams;
    static int numb;

    static Team[] teamArray = null;
    static Tournament[] tournArray = null;
    static Game[] gameArray=null;

    static ArrayList<String> gameList;
    static ArrayList<String> list;
    static ArrayList<String> teamList;


    public void setTournArray(Tournament[] tArray){
        tournArray = tArray;
    }


    public Tournament getTournAt(int pos){
        return tournArray[pos];
    }

        public void setTournIntent(Intent tournamentIntent){

            tournIntent = tournamentIntent;
        }
        public void setTeamIntent(Intent tIntent){

            teamIntent = tIntent;
        }
    public void setGameIntent(Intent gIntent){

        gameIntent = gIntent;
    }
        public Intent getTournIntent(){
        if(tournIntent != null)
            return tournIntent;
        else
            return null;
    }
    public Intent getTeamIntent(){
        if(teamIntent != null)
            return teamIntent;
        else
            return null;
    }
    public Intent getGameIntent(){
        if(gameIntent != null)
            return gameIntent;
        else
            return null;
    }

    public void setTournCreated(boolean created){
        tournCreated=created;
    }

    public void setGameArray(Game[] game){
        gameArray = game;
       numb = gameArray.length;
    }
    public Game[] getGameArray(){return gameArray;}

    public int getNumbGamesPlayed(){
        return numb;
    }
    public void setNumbGamesPlayed(int n){numb = n;}

    public ArrayList<String> getGameList(){
        return gameList;
    }
    public void setGameList(ArrayList<String> gList){
        gameList = gList;
    }

    public Team getTeamAt(int pos){

        return teamArray[pos];
    }

    public void setTeamArray(Team[] team){
        teamArray = team;
    }
    public Team[] getTeamArray(){return teamArray;}

    public void setNumberOfTeams(int numb){numberOfTeams=numb;}
    public int getNumberOfTeams(){return numberOfTeams;}

    public ArrayList<String> getTeamList(){
        return teamList;
    }
    public void setTeamList(ArrayList<String> tList){
        teamList = tList;
    }

        /**********GLOBAL FOR USER AUTHENTICATION******Aniela Opolski*****/

        /**
         * Hard code users into app --for demo
         */
        private static User admin = new User("ad", "a", "admin");
        private static User teamManager = new User("tm", "m", "tmanager");
        //max 10 users --for demo
        private static User[] currentUsers= new User[]{admin,teamManager,null,null,null,null,null,null,null,null};
        private static int userNumber=2;


    public static boolean isEmailValid(String email) {

        for(int i=0; i<userNumber; i++)
        {
            if((currentUsers[i].getUser()).equals(email))
            {
                return true;
            }
        }
        return false;
    }

    public static boolean isPasswordValid(String password, String email) {

        for(int i=0; i<userNumber; i++)
        {
            if((currentUsers[i].getUser()).equals(email))
            {
                if((currentUsers[i].getPassword()).equals(password))
                {
                    return true;
                }
            }
        }
        return false;

    }

    public static String getUserType(String email){
        String user="";
        for(int i=0; i<userNumber; i++)
        {
            if(currentUsers[i].getUser().equals(email))
            {
                user=currentUsers[i].getUserType();
            }
        }
        return user;
    }

    public static void addUserToSystem(User newUser)
    {
        currentUsers[userNumber]=newUser;
        userNumber++;
    }

    }

