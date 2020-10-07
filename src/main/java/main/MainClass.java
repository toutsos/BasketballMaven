package main;

import dao.CoachDao;
import dao.JPAUtil;
import dao.PlayerDao;
import entities.Training;
import entities.Stadium;
import entities.Player;
import entities.Team;
import entities.Game;
import entities.Coach;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
                System.out.println("For team infomation press 1");
                System.out.println("For coach infomation press 2");
                System.out.println("For trainings infomations press 3");
                System.out.println("For games infomations press 4");
                System.out.println("For stadium infomations press 5");
                System.out.println("To close programm press 6");
                System.out.println("---------------------------------------");
                Scanner key = new Scanner(System.in);

                try {
                    firstMenuFlag = key.nextInt();
                } catch (Exception e) {
                    System.out.println("Only numbers allowed!");
                }

                if (firstMenuFlag == 1) {
                    try {
                        System.out.println("---------------------------------------");
                        System.out.println("For team roster press 1");
                        System.out.println("To add player press 2");
                        System.out.println("To remove player press 3");
                        System.out.println("Finish press 4");
                        System.out.println("---------------------------------------");

                        int teamInformationMenu = key.nextInt();
                        if (teamInformationMenu == 1) {

                            PlayerDao pd = new PlayerDao();
                            List<Player> ls = pd.findAllPlayers();
                            if (ls.size() > 0) {
                                for (Player p : ls) {
                                    System.out.println(p);
                                }
                            } else {
                                System.out.println("Our roster is empty!");
                            }
                        } else if (teamInformationMenu == 2) {
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
//                        PAOK.addPlayer(newPlayer);
                                pd.save(newPlayer);

                                System.out.println("Player added! WELCOME TO PAOK!");
                                System.out.println("---------------------------------------");
                                System.out.println("Add new player?");
                                System.out.println("YES or NO");
                                flag1 = key.next();
                            }//while 
                        } else if (teamInformationMenu == 3) {
                              playerDelete();
//                            PAOK.removePlayer(nameRemove);
                        } else {
                            System.out.println("Press 1 , 2 , 3 or 4");
                        }//else
                    } catch (Exception e) {
                    }//try-catch

                } else if (firstMenuFlag == 2) {
                    System.out.println("---------------------------------------");
                    System.out.println("For coach history press 1");
                    System.out.println("To add new coach press 2");
                    System.out.println("To return press any other key");
                    System.out.println("---------------------------------------");
                    int coachMenu = key.nextInt();
                    try {
                        if (coachMenu == 1) {
                            showAllCoaches();
                        } else if (coachMenu == 2) {
                            createNewCoach(key);
                        } else {
                            System.out.println("Something went wrong please try again!");
                            break;
                        }//else
                    } catch (Exception e) {
                        System.out.println("inside try catch coach");
                                
                    }//TRY-CATCH

                } else if (firstMenuFlag == 3) {
                    System.out.println("---------------------------------------");
                    System.out.println("For trainings information press 1");
                    System.out.println("To add training press 2");
                    System.out.println("To return press any other key");
                    System.out.println("---------------------------------------");

                    try {
                        Scanner key4 = new Scanner(System.in);
                        int trainingMenu = key4.nextInt();
                        if (trainingMenu == 1) {
                            PAOK.showTrainings();
                        } else if (trainingMenu == 2) {
                            System.out.println("Give date of training at format yyyy-mm-dd");
                            String dateTraining = key.next(); //string dateTraining
                            LocalDate dateTr = LocalDate.parse(dateTraining, formatterDate); //convert to LocalDate

                            System.out.println("Give time (hh:mm)");
                            String timeTraining = key.next(); //String input for time training
                            LocalTime timeTr = LocalTime.parse(timeTraining, formatterTime);//convert to DateTime

                            LocalDateTime dateTimeTraining = LocalDateTime.of(dateTr, timeTr); //create LocalDateTIme stamp

                            System.out.println("Give stadium we will play");
                            String nameStadium = key.next();

                            Training newTraining = new Training(dateTimeTraining, new Stadium(nameStadium)); //create new training object
                            String flag3 = "YES"; //flag for add new player
                            while (flag3.equals("YES")) {
                                System.out.println("Give players name:");
                                Scanner key5 = new Scanner(System.in);
                                String name = key5.nextLine();
                                System.out.println("Give players rank:");
                                double rank = key5.nextDouble();
                                boolean flag2 = false; //flag to check if player exists in team
                                for (int j = 0; j < PAOK.players.size(); j++) {
                                    if (PAOK.players.get(j).getName().equals(name)) {
                                        Player newPlayer = new Player(name);
                                        newTraining.addPlayer(newPlayer);
                                        newTraining.addRank(rank);
                                        double plus = PAOK.players.get(j).getTrainings();
                                        PAOK.players.get(j).setTrainings(plus + 1);
                                        PAOK.players.get(j).setTotalRank(rank);
                                        flag2 = true;
                                    }//if   
                                }//for
                                if (flag2 == false) {
                                    System.out.println("---------------------------------------");
                                    System.out.println("This player does not belong to our team!");
                                }//else
                                System.out.println("---------------------------------------");
                                System.out.println("Do you want to add another player? YES or NO");
                                flag3 = key5.next();
                            }//while
                            PAOK.trainings.add(newTraining);
                            System.out.println("---------------------------------------");
                            System.out.println("Training added!");
                        } else {
                            continue;
                        }//else
                    } catch (Exception e) {
                    }//try-catch
                } else if (firstMenuFlag == 4) {
                    System.out.println("---------------------------------------");
                    System.out.println("For games information press 1");
                    System.out.println("To add new game press 2");
                    System.out.println("To complete game press 3");
                    System.out.println("To return press any other key");
                    System.out.println("---------------------------------------");
                    try {

                        Scanner key5 = new Scanner(System.in);
                        int gameMenu = key5.nextInt();
                        if (gameMenu == 1) {
                            PAOK.showGames();
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
                            PAOK.generateStartTen();
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
                    System.out.println("---------------------------------------");
                    System.out.println("For stadium information press 1");
                    System.out.println("To add new stadium press 2");
                    System.out.println("To delete stadium press 3");
                    System.out.println("To return press any other key");
                    System.out.println("---------------------------------------");
                    try {
                        Scanner key6 = new Scanner(System.in);
                        int a = key6.nextInt();
                        if (a == 1) {
                            PAOK.showStadiums();
                            break;
                        } else if (a == 2) {
                            System.out.println("Give name of the stadium");
                            key.nextLine();
                            String nameOfStadium = key.nextLine();
                            PAOK.addNewStadium(new Stadium(nameOfStadium));
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
            }//while
        } catch (Exception e) {
        }//try-catch

    }//main

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

    public static void showAllCoaches(){
        CoachDao cd = new CoachDao();
        List<Coach> list = cd.findAll();
        for(Coach c: list ){
            System.out.println(c);
        }
    }
    
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
        }
        }
    
}//class1
