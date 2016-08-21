package f.lucia.tournamentmakerapp;
/**
 * Created by leilacompaore and Nel on 15-12-01.
 * Attributes and methods asossiated with Teams
 */

public class Team implements Comparable<Team>{
    private String teamName;
    private String nPlayers;
    private String coachName;
    private String biography;
    private String cityOfOrigin;

    private int offsides=0;
    private int fouls=0;
    private int possession=0;
    private int rank=0;
    private int goals=0;

    private boolean off =true;
    static private int currPos= -1;
    static private int currTeams=0;

    public Team(){
        teamName = "default";
        nPlayers = "0";
        coachName = "default";
        biography = "default";
        cityOfOrigin = "default";
        currPos++;
        currTeams++;
    }

    public int compareTo(Team t){
        if (this.getGoals()>t.getGoals())
        {
            return 1;

        }
        else if (this.getGoals()==t.getGoals())
        {
            return 0;
        }
        else return -1;
    }

    public void setTeamName(String nTeamName){
        teamName = nTeamName;
    }
    public String getTeamName(){
        return teamName;
    }

    public void setGoals(int g) {
        goals = goals + g;
    }
    public int getGoals(){return goals;}

    public void setCoachName(String nCoachName){
        coachName = nCoachName;
    }
    public String getCoachName(){
        return coachName;
    }

    public void setTeamOffsides(int off){offsides = offsides+off;}
    public int getTeamOffsides(){return offsides;}

    public void setTeamFouls(int foul){fouls = fouls+ foul;}
    public int getTeamFouls(){return fouls;}

    public void setOffGames(boolean v){
       off=v;
    }
    public boolean getOffGames(){return off;}

    public void setTeamPoss(int pos){possession = possession+pos;}
    public int getTeamPoss(){return possession;}

    public void setBiography(String nBiography){
        biography = nBiography;
    }
    public String getBiography(){
        return biography;
    }

    public void setNPlayers(String nNPlayers){
        nPlayers = nNPlayers;
    }
    public String getNPlayers(){
        return nPlayers;
    }

    public void setCityOfOrigin(String nCityOfOrigin){
        cityOfOrigin = nCityOfOrigin;
    }
    public String getCityOfOrigin(){
        return cityOfOrigin;
    }

    public int getAmountOfTeams(){
        return currPos;
    }
    public int getTeamsInArray(){return currTeams;}

    public void setRank(int ranking){rank=ranking;}
    public int getRank(){return rank;}

}
