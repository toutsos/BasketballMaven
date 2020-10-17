package main;

import dao.CoachDao;
import dao.GameDao;
import dao.JPAUtil;
import dao.PlayerDao;
import dao.PlayerTrainingDao;
import dao.StadiumDao;
import dao.TrainingDao;
import entities.Training;
import entities.Stadium;
import entities.Player;
import entities.Team;
import entities.Game;
import entities.Coach;
import entities.PlayerTraining;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainClass extends JPAUtil<Object> {

    static DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    static DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm");
    static DateTimeFormatter formatterDateTime = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");

    public static void main(String[] args) {
          
        Team PAOK = new Team(); //creates our team
        findAndReplaceCoach(); //creates our first coach!
        System.out.println("Welcome to our team PAOK");

        int firstMenuFlag = 0; //for menu choice (to get into loop)
        try {
            while (firstMenuFlag != 6) { //main screen  
                System.out.println("---------------------------------------");
                showFirstMenu();
                Scanner key = new Scanner(System.in);
                try {
                    firstMenuFlag = key.nextInt();
                } catch (Exception e) {
                    System.out.println("Only numbers allowed!");
                }//catch

                if (firstMenuFlag == 1) {
                    try {
                        showTeamInformationMenu();
                        int teamInformationMenu = key.nextInt();
                        if (teamInformationMenu == 1) {
                            showAllPlayers();
                        } else if (teamInformationMenu == 2) {
                            addNewPlayer(key);
                        } else if (teamInformationMenu == 3) {
                            playerDelete();
                        } else {
                            System.out.println("Press 1 , 2 , 3 or 4");
                        }//else
                    } catch (Exception e) {
                    }//try-catch

                } else if (firstMenuFlag == 2) {
                    try {
                        showCoachMenu();
                        int coachMenu = key.nextInt();
                        if (coachMenu == 1) {
                            showAllCoaches();
                        } else if (coachMenu == 2) {
                            createNewCoach(key);
                        } else {
                            continue;
                            //break;
                        }//else
                    } catch (Exception e) {
                    }//TRY-CATCH

                } else if (firstMenuFlag == 3) {
                    showTrainingsMenu();
                    try {
                        Scanner key4 = new Scanner(System.in);
                        int trainingMenu = key4.nextInt();
                        if (trainingMenu == 1) {
                            showAllTrainings();
                        } else if (trainingMenu == 2) {
                            System.out.println("Give date of training at format yyyy-mm-dd");
                            String dateTraining = key.next(); //string dateTraining
                            LocalDate dateTr = LocalDate.parse(dateTraining, formatterDate); //convert to LocalDate

                            System.out.println("Give time (hh:mm)");
                            String timeTraining = key.next(); //String input for time training
                            LocalTime timeTr = LocalTime.parse(timeTraining, formatterTime);//convert to DateTime

                            LocalDateTime dateTimeTraining = LocalDateTime.of(dateTr, timeTr); //create LocalDateTIme stamp
                            TrainingDao td = new TrainingDao();
                            StadiumDao sd = new StadiumDao();
                            
                            List<Stadium> allStadium = sd.findAllStadiums();
                            
                            //test
                            System.out.println("Choose a stadium from list below:");
                            for(int i=0;i<allStadium.size();i++){
                                System.out.println(allStadium.get(i));
                            }
                            
                            Boolean flagStadiumExistance = false;
                            while(flagStadiumExistance==false){
                                System.out.println("Give stadium we will play:");
                                String nameStadium = key.next();
                                for (int i=0; i<allStadium.size();i++){
                                    if (allStadium.get(i).getName().equals(nameStadium)){
                                            Stadium stadium = sd.findStadiumFromName(nameStadium);
                                            Training newTraining = new Training(dateTimeTraining,stadium);
                                            td.save(newTraining);
                                            stadium.getTrainings().add(newTraining);
                                            flagStadiumExistance=true;
                                            System.out.println("Training added!");
                                            continue;
                                    }//if
                                }//for
                                    
                                if(flagStadiumExistance==false){
                                    System.out.println("This stadium doesnt exist");
                                    System.out.println("Do you want to add it? (YES or NO)");
                                    String answer = key.next();
                                    if(answer.equalsIgnoreCase("yes")){
                                        
                                        Stadium newStadium = new Stadium(nameStadium);
                                        
                                        Training newTraining = new Training(dateTimeTraining,newStadium);
                                        td.save(newTraining);
                                        
                                        newStadium.getTrainings().add(newTraining);
                                        sd.save(newStadium);
                                        
                                        flagStadiumExistance=true;
                                        System.out.println("Training added!");
                                    }else{
                                        break;
                                    }//else
                                }//if
                            }//while flagStadiumExistance=false;
                              
                            Training training = td.findTrainingFromDate(dateTimeTraining);
                            Training passTraining = td.getTrainingWithoutClosingEm(training.getId());
                            String flag3 = "YES"; //flag for add new player
                            PlayerTrainingDao ptd = new PlayerTrainingDao();
                            while (flag3.equals("YES")) {
                                //Training passTraining = newTraining;
                                System.out.println("Give players name:");
                                Scanner key5 = new Scanner(System.in);
                                String name = key5.nextLine();
                                System.out.println("Give players rank:");
                                int rank = key5.nextInt();
                                boolean flag2 = false; //flag to check if player exists in team
                                PlayerDao pd = new PlayerDao();
                                Player player = pd.findPlayerFromName(name); //finds the player from db
                                Player newPlayer = pd.getPlayerWithoutClosingEm(player.getId()); // i keep transactions for the player open to modify attributes
                                if (player != null) {
                                    PlayerTraining playerTraining = new PlayerTraining (rank,newPlayer,passTraining);
                                    newPlayer.setHeight(500);
                                    System.out.println("--------------1---------------");
                                    newPlayer.getTrainings().add(playerTraining);                                   //AYTO DIMIOURGEI TO PROBLIMA why??????
                                    System.out.println(newPlayer.getTrainings().toString());
                                    
                                    System.out.println("--------------2---------------");
                                    newPlayer.setTotalTrainings(newPlayer.getTotalTrainings()+1);
                                    //System.out.println("--------------3---------------");
                                    //newPlayer.setTotalRank(newPlayer.getTotalRank()+rank);
                                    System.out.println("--------------4---------------");
                                    System.out.println(newPlayer);
                                    pd.update(newPlayer);
                                   // pd.updatePlayer(newPlayer);                                                     //WHY??????????????
                                    System.out.println("--------------5---------------");
                                    //passTraining.getPlayers().add(playerTraining);
                                    System.out.println("--------------6---------------");
                                    td.update(passTraining);
                                    System.out.println("--------------7---------------");
                                    ptd.save(playerTraining);
                                    System.out.println("success");
                                           
                                    
                                    
                                            
                                    
                                    flag2 = true;
                                }
                                if (flag2 == false) {
                                    System.out.println("---------------------------------------");
                                    System.out.println("This player does not belong to our team!");
                                }//else
                                System.out.println("---------------------------------------");
                                System.out.println("Do you want to add another player? YES or NO");
                                flag3 = key5.next();
                            }//while
                        }//if   
                    } catch (Exception e) {
                    }//try-catch
                    
                    
                } else if (firstMenuFlag == 4) {
                    showGameInformationMenu();
                    
                    try {

                        Scanner key5 = new Scanner(System.in);
                        int gameMenu = key5.nextInt();
                        if (gameMenu == 1) {
                            showAllGames();
                        } else if (gameMenu == 2) {
                            System.out.println("Give opponent");
                            key.nextLine();
                            String opponent = key.nextLine();
                            System.out.println("Give date of game at pattern (yyyy/mm/dd)");
                            String dateGame = key.next();
                            LocalDate newDateGame = LocalDate.parse(dateGame, formatterDate); //convert to LocalDate
                            System.out.println("Give time (hh:mm)");
                            String timeGame = key.next();
                            LocalTime newTimeGame = LocalTime.parse(timeGame, formatterTime); //convert to LocalTime
                            LocalDateTime newGameLocalDateTime = LocalDateTime.of(newDateGame, newTimeGame);
                            System.out.println("Give stadium we will play");
                            String nameStadium = key.next();

                            //We create new game
                            Game newGame = new Game(opponent, newGameLocalDateTime, new Stadium(nameStadium));
                            PAOK.addNewGame(newGame);
                            //PAOK.generateStartTen();
                            System.out.println("Players that join team at this game are: ");
                            if (PAOK.allPlayersGame.size() >= 10) {
                                for (int i = 0; i < 10; i++) {
                                    System.out.println(PAOK.allPlayersGame.get(i));
                                    newGame.playersGame.add(PAOK.allPlayersGame.get(i));
                                }
                                System.out.println("-------------------------------------------------");
                                System.out.println("STARTING *5*");
                                System.out.println("-------------------------------------------------");
                                for (int i = 0; i < 5; i++) {
                                    System.out.println(PAOK.allPlayersGame.get(i));
                                }
                            } else {
                                System.out.println("-------------------------------------------------");
                                System.out.println("!!!!! We don't have 10 players in our team!!!!!!");
                                System.out.println("-------------------------------------------------");
                                System.out.println("WE will play with:");
                                for (int i = 0; i < PAOK.allPlayersGame.size(); i++) {
                                    System.out.println(PAOK.allPlayersGame.get(i));
                                    newGame.playersGame.add(PAOK.allPlayersGame.get(i));
                                }//for   
                            }//else
                        } else if (gameMenu == 3) {
                            System.out.println("Which game do you want to complete?");
                            System.out.println("Type date of game");
                            Scanner key6 = new Scanner(System.in);
                            String gameDateCompl = key6.next();
                            LocalDate dateGameComplete = LocalDate.parse(gameDateCompl);
                            for (int i = 0; i < PAOK.games.size(); i++) {
                                if (PAOK.games.get(i).getDate().equals(dateGameComplete)) {
                                    for (int j = 0; j < PAOK.games.get(i).getPlayersGame().size(); j++) {
                                        System.out.println("Give points that scored: " + PAOK.games.get(i).getPlayersGame().get(j));
                                        Scanner key7 = new Scanner(System.in);
                                        int gamePoints = key7.nextInt();
                                        PAOK.games.get(i).playersPoints.add(gamePoints);
                                    }//for
                                }//if
                            }//for
                        }//else if
                    } catch (Exception e) {
                    }//try - catch
                } else if (firstMenuFlag == 5) {
                    
                    StadiumDao sd = new StadiumDao();
                    showStadiumMenu();
                    try {
                        Scanner key6 = new Scanner(System.in);
                        int stadiumMenuChoice = key6.nextInt();
                        if (stadiumMenuChoice == 1) {
                            showAllStadiums();
                            break;
                        } else if (stadiumMenuChoice == 2) {
                            System.out.println("Give name of the stadium");
                            key.nextLine();
                            String nameOfStadium = key.nextLine();
                            Stadium newStadium = new Stadium (nameOfStadium);
                            sd.save(newStadium);
                            System.out.println("Stadium "+nameOfStadium+" added!");
                        }
                    } catch (Exception e) {
                    }

                } else if (firstMenuFlag == 6) {
                    System.out.println("Good Luck PAOK!");
                    break;
                } else {
                    System.out.println("---------------------------------------");
                    System.out.println("Wrong number");
                }//else
            }//while firstMenu
        } catch (Exception e) {
        }//try-catch

    }//main

    
    /* METHODS*/
    
    
    /**
     * Updates if Coach exists, else Insert
     *
     * @param c
     */
    public static void findAndReplaceCoach() {
        CoachDao cd = new CoachDao();
        List<Coach> list = cd.findAll();
        String myDate = "2017-05-15";
        LocalDate date = LocalDate.parse(myDate, formatterDate);
        Coach coachPaok = new Coach("Fereira", 23333, date);
        if (list.size() == 0) {
            cd.save(coachPaok);
        }

    }//findAndReplaceCoach
    
    
    /**
     * show all coaches that assigned to our team
     */
    public static void showAllCoaches(){
        CoachDao cd = new CoachDao();
        List<Coach> list = cd.findAll();
        for(Coach c: list ){
            System.out.println(c);
        }
    }
    
    /**
     * Create a new Coach
     * @param key
     */
    public static void createNewCoach(Scanner key){
        CoachDao cd = new CoachDao();
        System.out.println("Give name");
        key.nextLine();
        String name = key.nextLine();
        System.out.println("Give salary");
        int salary = key.nextInt();
        System.out.println("Give Start date at format: yyyy-mm-dd");
        String dateStart = key.next();
        LocalDate localDate = LocalDate.parse(dateStart, formatterDate);
        Coach coachPaok = new Coach(name,salary,localDate);
        cd.save(coachPaok); //override Coach
        System.out.println("Conglatulations our new coach is:");
        System.out.println(coachPaok.toString());
    }
    
    /**
     * Delete a player from his name
     */
    public static void playerDelete(){
        System.out.println("Give player's name to remove");
        Scanner key2 = new Scanner(System.in);
        String nameRemove = key2.next();
        PlayerDao pd = new PlayerDao();
        Player removedPlayer  = pd.findPlayerFromName(nameRemove);
        int id = removedPlayer.getId();
        if(removedPlayer != null){
            pd.delete(id);
            System.out.println("Player "+nameRemove+" removed from our team!");
        }else{
            System.out.println("This players doesnt belong to our team!");
        }//else
    }//playerDelete
    
    /**
     * Shows all trainings
     */
    public static void showAllTrainings(){
        TrainingDao td = new TrainingDao();
        List<Training> list = td.findAll() ;
        for(Training c: list ){
            System.out.println(c);
        }//for
    }//showAllTrainings
    
    /**
     * Shows all games
     */
    public static void showAllGames(){
        GameDao gd = new GameDao();
        List<Game> list = gd.findAll();
        System.out.println("Games:");
        for(Game g: list){
            System.out.println(g);
        }//for
    }//showAllGames

    /**
     * show all team's players
     */
    public static void showAllPlayers(){
        PlayerDao pd = new PlayerDao();
        List<Player> ls = pd.findAllPlayers();
        if (ls.size() > 0) {
            for (Player p : ls) {
                System.out.println(p);
            }
        } else {
            System.out.println("Our roster is empty!");
        }
    }
    
    public static void showAllStadiums(){
        StadiumDao sd = new StadiumDao();
        List<Stadium> ls = new ArrayList<Stadium>();
        ls = sd.findAllStadiums();
        if (ls.size() > 0) {
            for (Stadium p : ls) {
                System.out.println(p);
            }
        } else {
            System.out.println("We dont have any stadium saved!");
        }
    }
    
    
    /**
     * Creates a new Player for our team
     * @param key 
     */
    public static void addNewPlayer(Scanner key){
    String flag1 = "YES";
        while (flag1.equals("YES")) {
            System.out.println("Write players information");
            String name;
            int age;
            int phone;
            int height;
            double weight;
            PlayerDao pd = new PlayerDao();
            System.out.println("Name:");
            key.nextLine();
            name = key.nextLine();
            System.out.println("Age:");
            age = key.nextInt();
            System.out.println("Phone number:");
            phone = key.nextInt();
            System.out.println("Height (cm):");
            height = key.nextInt();
            System.out.println("Weight(kg):");
            weight = key.nextInt();
            Player newPlayer = new Player(name, age, phone, height, weight);
            pd.save(newPlayer);
            System.out.println("Player added! WELCOME TO PAOK!");
            System.out.println("---------------------------------------");
            System.out.println("Add new player?");
            System.out.println("YES or NO");
            flag1 = key.next();
        }//while 
    }
    
    /**
     * Prints only team information menu
     */
    public static void showTeamInformationMenu(){
        System.out.println("---------------------------------------");
        System.out.println("For team roster press 1");
        System.out.println("To add player press 2");
        System.out.println("To remove player press 3");
        System.out.println("Finish press 4");
        System.out.println("---------------------------------------");
    }
    
    /**
     * Prints only first menu
     */
    public static void showFirstMenu(){
        System.out.println("For team infomation press 1");
        System.out.println("For coach infomation press 2");
        System.out.println("For trainings infomations press 3");
        System.out.println("For games infomations press 4");
        System.out.println("For stadium infomations press 5");
        System.out.println("To close programm press 6");
        System.out.println("---------------------------------------");
    }
    
    /**
     * Prints only coach information menu
     */
    public static void showCoachMenu(){
        System.out.println("---------------------------------------");
        System.out.println("For coach history press 1");
        System.out.println("To add new coach press 2");
        System.out.println("To return press any other key");
        System.out.println("---------------------------------------");
    }
    
    /**
     * Prints only team's trainings menu
     */
    public static void showTrainingsMenu(){
        System.out.println("---------------------------------------");
        System.out.println("For trainings information press 1");
        System.out.println("To add training press 2");
        System.out.println("To return press any other key");
        System.out.println("---------------------------------------");
    }
    
    /**
     * Prints only team's games menu
     */
    public static void showGameInformationMenu(){
        System.out.println("---------------------------------------");
        System.out.println("For games information press 1");
        System.out.println("To add new game press 2");
        System.out.println("To complete game press 3");
        System.out.println("To return press any other key");
        System.out.println("---------------------------------------");
    }
    
    /**
     * Prints only Stadium information menu
     */
    public static void showStadiumMenu(){
        System.out.println("---------------------------------------");
        System.out.println("For stadium information press 1");
        System.out.println("To add new stadium press 2");
        System.out.println("To delete stadium press 3");
        System.out.println("To return press any other key");
        System.out.println("---------------------------------------");
    }
    
    
    
}//class
