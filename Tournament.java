package f.lucia.tournamentmakerapp;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by tayo on 03/12/15.
 */
public abstract class Tournament {

    private int maxNumOfTeams;
    private int minNumOfTeams;
    private String name;
    private  Team [] teamArray;
    private Game[] gameArray;

    ArrayList<String> teamList;
    private ArrayList<String> gameList;
    int numTeams;


    private boolean isOver=false;

    static private int teamIndex=-1;
    private boolean started=false;
    private boolean ended=false;

    public Tournament (int maxTeams, int minTeams,String name)
    {
        setMaxNumOfTeams(maxTeams);
        setMinNumOfTeams(minTeams);
        this.setName(name);

        teamIndex++;
    }

    public void addTeams( Team [] team,ArrayList<String> tList, int numbOfTeams)
    {
        numTeams=numbOfTeams;
        if (getMaxNumOfTeams() >= numTeams)
        {

            teamList = tList;
            teamArray = team;

       }

    }


    public int getTournIndex(){
        return teamIndex;
    }


    public abstract void scheduleGames();

    public abstract void elimination(Game gamePlayed);

    public abstract Team determineWinner();
    public abstract Team[] determineRanking();
    public abstract String getType();


    public int getMaxNumOfTeams() {
        return maxNumOfTeams;
    }

    public void setMaxNumOfTeams(int maxNumOfTeams) {
        this.maxNumOfTeams = maxNumOfTeams;
    }

    public int getMinNumOfTeams() {
        return minNumOfTeams;
    }

    public void setMinNumOfTeams(int minNumOfTeams) {
        this.minNumOfTeams = minNumOfTeams;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public ArrayList<String> getTeamList() {
        return teamList;
    }


    public ArrayList<String> getGameList() {
        return gameList;
    }

    public void setGameList(ArrayList<String> gList) {
        gameList = gList;
    }



    public Game[] getGameArray(){return gameArray;}
    public void setGameArray(Game[] games){gameArray=games;}

    public Team[] getTeamArray(){return teamArray;}
//    public void setTeamArray(Team[] games){teamArray=games;}


    public boolean getTournStarted(){return started;}
    public void setTournStarted(boolean start){started=start;}


    public boolean getTournEnded(){return ended;}
    public void setTournEnded(boolean end){ended=end;}
}




