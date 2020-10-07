
package entities;
import dao.JPAUtil;
import dao.PlayerDao;
import static java.util.Collections.list;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;



public class Team {
    
    protected String name;
    public ArrayList<Player> players = new ArrayList<Player>(); //all team players
    public ArrayList<Training> trainings = new ArrayList<Training>(); //all trainings
    public ArrayList<Game> games = new ArrayList<Game>(); // all games
    public ArrayList<Stadium> stadiums = new ArrayList<Stadium>();
    protected String coach;

    public ArrayList<String> allPlayersGame = new ArrayList<String>(); //arraylist for sorting
    public ArrayList<Double> allPlayersRanking = new ArrayList<Double>(); //arraylist for sorting
    public ArrayList<Double> allPlayersTrainings = new ArrayList<Double>(); //arraylist for sorting
    
    
    // CONSTRUCTORS //
    public Team() {
    }


    // GETTERS - SETTERS //
    
    public String getName() {
        return name;
    }

    public ArrayList getPlayers() {
        return players; 
    }

    public String getCoach() {
        return coach;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlayers(ArrayList players) {
        this.players = players;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    // --METHODS-- //
    
    public void addPlayer(Player x){
        players.add(x);
    }
    
    public void removePlayer(String x){
        for( int i=0; i<players.size();i++){
          String name = players.get(i).getName();
          if(name.equals(x)){
              players.remove(i);
              System.out.println("Player successfully removed");
              break;
          }//if
        }//for
    }//removePlayer
     
    public void showPlayers (){
        PlayerDao pd = new PlayerDao();
        List <Player> ls = new ArrayList<>();
        ls = pd.findAllPlayers();
        for(int i=0;i<ls.size();i++){
            System.out.println(ls.get(i).toString());
        }//for
    }//showPlayers
    
    public void showCoach (){
        System.out.println(coach.toString());
    }
    
    public void addTraining(Training x){
        trainings.add(x);
    }
   
    public void showTrainings (){
        for (int i=0;i<trainings.size();i++){
           trainings.get(i).showTraining();
        }
        if(trainings.size()==0){
            System.out.println("---------------------------------------");
            System.out.println("No scheduled trainings!");
        }//if
    }//showTrainings
    
    public void showGames(){
        for (int i=0;i<games.size();i++){
           games.get(i).showGameDetails();
        }//for
    }//showTrainings
    
    public void addNewGame(Game x){
        games.add(x);
    }//addNewGame
     
    public void addNewStadium(Stadium x){
        stadiums.add(x);
        System.out.println("Stadium added!");
               
    }//addNewGame
    
    public void showStadiums (){
        for (int i=0;i<stadiums.size();i++){
            System.out.println("Stadium Name: "+stadiums.get(i).getName());
        }//for
        if (stadiums.size()==0){
            System.out.println("We dont have available stadiums, please add!");
        }
    }//showStadiums
    
     
     
    public void generateStartTen(){
        for (int i=0;i<players.size();i++){ //make a new Arrays to sort players
           allPlayersGame.add(players.get(i).getName());
           allPlayersRanking.add(players.get(i).getTotalRank()/players.get(i).getTrainings());
           allPlayersTrainings.add(players.get(i).getTrainings());
        }//for
       
        for (int i=0;i<allPlayersTrainings.size()-1;i++){ //sort all players with trainings
            boolean flag = false;
            for(int j=0;j<allPlayersTrainings.size()-1-i;j++){
               if(allPlayersTrainings.get(j)<allPlayersTrainings.get(j+1)){
                   flag=true;
                   double temp1 = allPlayersTrainings.get(j);
                   double temp2 = allPlayersTrainings.get(j+1);
                   allPlayersTrainings.set(j,temp2);
                   allPlayersTrainings.set(j+1,temp1);
                   
                   String tempName1 = allPlayersGame.get(j);
                   String tempName2 = allPlayersGame.get(j+1);
                   allPlayersGame.set(j, tempName2);
                   allPlayersGame.set(j+1, tempName1);
                   
                    double temp3 = allPlayersRanking.get(j);
                    double temp4 = allPlayersRanking.get(j+1);
                    allPlayersRanking.set(j,temp4);
                    allPlayersRanking.set(j+1,temp3);
               }//if
           }//for
       }//for
       
       if(allPlayersGame.size()>=10){
       //sort players with their ranking i<8 because we want to sort only first 10 players!
            for(int i=0 ; i<9;i++){
                boolean flag = false; 
                for(int j=0;j<9-i;j++){
                    if(allPlayersRanking.get(j)<allPlayersRanking.get(j+1)){
                        flag= true;
                        double temp1 = allPlayersRanking.get(j);
                        double temp2 = allPlayersRanking.get(j+1);
                        allPlayersRanking.set(j,temp2);
                        allPlayersRanking.set(j+1,temp1);

                        String tempName1 = allPlayersGame.get(j);
                        String tempName2 = allPlayersGame.get(j+1);
                        allPlayersGame.set(j, tempName2);
                        allPlayersGame.set(j+1, tempName1);
                    }//if
                }//for
            if(flag==false){
                break;
            }//if
            }//for
       }else{
            for(int i=0 ; i<allPlayersRanking.size()-1;i++){
                boolean flag = false; 
                for(int j=0;j<allPlayersRanking.size()-1-i;j++){
                    if(allPlayersRanking.get(j)<allPlayersRanking.get(j+1)){
                        flag= true;
                        double temp1 = allPlayersRanking.get(j);
                        double temp2 = allPlayersRanking.get(j+1);
                        allPlayersRanking.set(j,temp2);
                        allPlayersRanking.set(j+1,temp1);

                        String tempName1 = allPlayersGame.get(j);
                        String tempName2 = allPlayersGame.get(j+1);
                        allPlayersGame.set(j, tempName2);
                        allPlayersGame.set(j+1, tempName1);
                    }//if
                }//for
            if(flag==false){
                break;
            }//if
            }//for
       }//else
    }//generateStartTen
       
     
}//team
