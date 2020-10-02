
package entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Game {
    public String opponent;
    public LocalDateTime gameDateTime;
    public ArrayList<String> playersGame = new ArrayList<String>();
    public ArrayList<Integer> playersPoints = new ArrayList<Integer>();
    public Stadium gameStadium;

    
        //CONSTRUCTOR//
    public Game(String opponent, LocalDateTime gameDateTime, Stadium stadium) {
        this.opponent = opponent;
        this.gameDateTime = gameDateTime;
        this.gameStadium=stadium;
    }
    
        //GETTERS-SETTERS
    
    public String getOpponent() {
        return opponent;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }
      
    public ArrayList<String> getPlayersGame() {
        return playersGame;
    }

    public void setPlayersGame(ArrayList<String> playersGame) {
        this.playersGame = playersGame;
    }

    public ArrayList<Integer> getPlayersPoints() {
        return playersPoints;
    }

    public void setPlayersPoints(ArrayList<Integer> playersPoints) {
        this.playersPoints = playersPoints;
    }
    
    
    public LocalDateTime getGameDateTime() {
        return gameDateTime;
    }

    public void setGameDateTime(LocalDateTime gameDateTime) {
        this.gameDateTime = gameDateTime;
    }
    
        public Stadium getGameStadium() {
        return gameStadium;
    }

    public void setGameStadium(Stadium gameStadium) {
        this.gameStadium = gameStadium;
    }
        

    //METHODS//
    
    public void showGameDetails() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Game VS "+opponent+" Date: "+getDate()+" ,Stadium: "+gameStadium.getName());
        System.out.println("Players that participated:");
        for (int i=0; i<playersGame.size(); i++){
            System.out.println("name: "+playersGame.get(i)+" points: "+playersPoints.get(i));
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }    
    
    /**
     * Converts LocalDateTIme of game to LocalDate for equals reasons
     * @param localGameTime
     * @return LocalDate
     */
    public LocalDate getDate () {
        LocalDate newDate = gameDateTime.toLocalDate();
        return newDate;
    }//getDate



}//class
