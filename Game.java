package f.lucia.tournamentmakerapp;

/**
 * Created by tayo on 01/12/15.
 * Displays the attributes and methods related to Games
 */
public class Game {
    Team team1;
    Team team2;

    private int team1Goals=0;
    private int team2Goals=0;

    private int team1Offsides=0;
    private int team2Offsides=0;

    private int team1Fouls=0;
    private int team2Fouls=0;

    private int team1Possesion=0;
    private int team2Possesion=0;

    private Team[] teamsInGame = new Team[2];
    boolean detailsSet = false;


    public Game(Team teamA,Team teamB) {
        team1=teamA;
        team2= teamB;

        teamsInGame[0]=teamA;
        teamsInGame[1]=teamB;
    }

    public Team[] getTeamsInGame(){
    return teamsInGame;
}


    public void setGameDetailsSet(boolean set){
        detailsSet=set;
    }
    public boolean getGameDetailsSet(){
        return detailsSet;
    }


    public void setGameGoals(Team t,int goals){
        if(teamsInGame[0].getTeamName().equals(t.getTeamName())){
            team1Goals = goals;
        }
        else{
            team2Goals = goals;
        }
    }
    public int getGameGoals(Team t){
        if(teamsInGame[0].getTeamName().equals(t.getTeamName()))
            return team1Goals;
        else{
            return team2Goals;
        }
    }

    public void setGameOffsides(Team t,int off){
        if(teamsInGame[0].getTeamName().equals(t.getTeamName())){
            team1Offsides = off;
        }
        else{
            team2Offsides = off;
        }
    }
    public int getGameOffsides(Team t){
        if(teamsInGame[0].getTeamName().equals(t.getTeamName()))
            return team1Offsides;
        else{
            return team2Offsides;
        }
    }

    public void setGameFouls(Team t,int foul){
        if(teamsInGame[0].getTeamName().equals(t.getTeamName())){
            team1Fouls = foul;
        }
        else{
            team2Fouls = foul;
        }
    }
    public int getGameFouls(Team t){
        if(teamsInGame[0].getTeamName().equals(t.getTeamName()))
            return team1Fouls;
        else{
            return team2Fouls;
        }
    }

    public void setGamePoss(Team t,int p){
        if(teamsInGame[0].getTeamName().equals(t.getTeamName())){
            team1Possesion = p;
        }
        else{
            team2Possesion = p;
        }
    }
    public int getGamePoss(Team t){
        if(teamsInGame[0].getTeamName().equals(t.getTeamName()))
            return team1Possesion;
        else{
            return team2Possesion;
        }
    }

    public Team getWinner() {

        if (team1Goals > team2Goals)
            return team1;
        else
            return team2;

    }


}