package f.lucia.tournamentmakerapp;

import java.util.ArrayList;

/**
 * Created by Nel on 15-12-04.
 * The code behind the KnockOut-type tournament
 */
public class KnockOut extends Tournament {

    private Team[] teams;
    private Game[] gameSchedule;

    private int roundNum=0;

    private String[] gameNames;
    static ArrayList<String> games;
    private Team winner;

    int teamCounter;

    //the following is a counter to keep track of the games played
    private int gamesPlayed;
    boolean started = false;

    public KnockOut(int maxTeams, int minTeams, String name) {
        super(maxTeams, minTeams, name);
    }

    public String getType() {
        return "Knock Out";
    }


    @Override
    public void scheduleGames() {

        //check if the games have been scheduled before
        if (!started) {
            //get the teams
            teams = getTeamArray();
            started = true;
        }
        //otherwise continue with the teams list set by elimination
        int numTeams = teams.length;

        //if there is one team remaining
        if (numTeams == 1) {
            winner = teams[0];
        } else {
            //set amount of games in the first round of the tournament --this is subject to change in next round
            gameSchedule = new Game[(numTeams / (int)Math.pow(2,roundNum+1))];
            gameNames = new String[(numTeams / (int)Math.pow(2,roundNum+1))];
            //counter for the amount of games played
            gamesPlayed = gameSchedule.length;


            int j = gamesPlayed;
            //set a schedule for the round
            for (int i = 0; i < gamesPlayed; i++) {
                gameSchedule[i] = new Game(teams[i], teams[j]);
                gameNames[i] = "Game " + (i+1);
                j++;
            }

            games = new ArrayList<String>();
            for (int i = 0; i < gamesPlayed; i++) {
                games.add(gameNames[i]);
            }
            setGameArray(gameSchedule);
            setGameList(games);


                //set new array for next round
                teams = new Team[numTeams / 2];
                teamCounter=0;
        }

    }

    //when game finished, winners are entered into team array

    public void elimination(Game gamePlayed){

        teams[teamCounter++] = gamePlayed.getWinner();

    }


    public Team[] determineRanking() {

        Team temp;
        for (int i = 0; i < teams.length - 1; i++) {

            for (int j = 1; j < teams.length - i; j++) {
                if (teams[j - 1].getRank() > (teams[j].getRank())) {
                    temp = teams[j - 1];
                    teams[j - 1] = teams[j];
                    teams[j] = temp;
                }
            }
        }
        return teams;
    }

    public Team determineWinner() {

        return winner;
    }

    public ArrayList getGames() {
        return games;
    }
}