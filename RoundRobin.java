package f.lucia.tournamentmakerapp;

/**
 * Created by tayo on 03/12/15.
 * Code begind the RoundRobin-type tournament
 */

        import java.util.ArrayList;



public class RoundRobin extends Tournament {

    static ArrayList<String>games;
    Team[] tm;
    public RoundRobin(int maxTeams, int minTeams,String name){
        super(maxTeams,minTeams,name);

    }

    public void elimination(Game g){}
    public String getType(){
        return "Round Robin";
    }

    public void scheduleGames(){
        ArrayList<String>teams =getTeamList();
        tm = getTeamArray();

        Game []tempGame=new Game [(teams.size()*(teams.size()+1))/2];
        String[] currGames = new String[(teams.size()*(teams.size()+1))/2];

        if (super.getTeamList().size()>=super.getMinNumOfTeams()){

            int gameNumb=1;
            int counter=0;
            tempGame=new Game [((teams.size()-1)*(teams.size()))/2];
            for (int i=0; i<teams.size();i++){
                for (int j=i+1; j<getTeamList().size();j++){

                    //spreads out thematches.
                    // If you put them in the order they are created the first team does all their games in a rowjust trust
                    // tempGame [(1+i*teams.size())+teams.size()-j]= new Game (teams.get(i),teams.get(j));
                    tempGame [counter]= new Game (tm[i],tm[j]);
                    currGames[counter]="Game "+gameNumb;

                    counter++;
                    gameNumb++;
                }
            }

            games= new ArrayList<String>();

            for(int i=0; i < gameNumb-1;i++){
                games.add(currGames[i]);
            }

        }

        setGameArray(tempGame);
        setGameList(games);


    }


    public Team determineWinner() {

        int max = 0;

        Team maxTeam = new Team();

        for (int j = 0; j < getTeamList().size(); j++) {
            if (tm[j].getGoals() > max) {
                maxTeam = tm[j];
                max = maxTeam.getGoals();
            }

        }
        return maxTeam;
    }

    public Team [] determineRanking() {

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