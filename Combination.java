package f.lucia.tournamentmakerapp;

/**
 * Created by Nel and Leila on 2015-12-04.
 * This is the code behind the combination-type tournament
 */
import java.util.ArrayList;


public class Combination extends Tournament {

    private Team winner = null;
    boolean started = false;
    private Team[] tm;
    static ArrayList<String> games;
    private int numTeams;

    public Combination(int maxTeams, int minTeams,String name){
        super(maxTeams,minTeams,name);
    }
    public String getType() {
        return "Combination";
    }


    //schedule games in a round using round robin algorithm
    public void scheduleGames() {


        ArrayList<String> teams = getTeamList();
        if (!started) {
            tm = getTeamArray();
            started =true;
        }
        //get the size of the team list
        numTeams = tm.length;
        //if there is one team remainingâ€¨
        if (numTeams == 1) {
            winner = tm[0];
        } else {
            //Tayo's round robin algorithmâ€¨â€¨
            Game[] tempGame = new Game[(teams.size() * (teams.size() + 1)) / 2];
            String[] currGames = new String[(teams.size() * (teams.size() + 1)) / 2];
            if (numTeams >= super.getMinNumOfTeams()) {
                int gameNumb = 1;
                int counter = 0;
                tempGame = new Game[((teams.size() - 1) * (teams.size())) / 2];
                for (int i = 0; i < numTeams; i++) {
                    for (int j = i + 1; j < numTeams; j++) {
                        tempGame[counter] = new Game(tm[i], tm[j]);
                        currGames[counter] = "Game " + gameNumb;
                        counter++;
                        gameNumb++;
                    }

                    games = new ArrayList<String>();


                }

                for (int i = 0; i < gameNumb - 1; i++)
                {
                    games.add(currGames[i]);
                }
            }
            setGameArray(tempGame);
            setGameList(games);
        }
    }


    //eliminate half the teams based on the amount of goals scored
    public void elimination(Game gamePlayed) {
        //sort the array in descending order
        tm = determineRanking();

        //take only the top half of the array... if odd floor the halfed number
        Team[] temp = new Team[numTeams/2];
        for(int i =0; i<numTeams/2; i++)
        {
            temp[i]= tm[i];
        }
        tm = temp;
    }


    //return the winner of the tournament
    public Team determineWinner() {
        return winner;
    }

    //Tayo's sorting algorithm
    public Team[] determineRanking() {
        Game[] games = new Game[getGameList().size()];
        games = getGameArray();

        Team temp;
        for(int i=0; i < tm.length-1; i++) {

            for (int j = 1; j < tm.length - i; j++) {
                if (tm[j - 1].compareTo(tm[j]) < 0) {
                    temp = tm[j - 1];
                    tm[j - 1] = tm[j];
                    tm[j] = temp;
                }
            }
        }


        return tm;
    }
}
