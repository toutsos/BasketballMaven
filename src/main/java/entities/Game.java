
package entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="game")
public class Game implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    
    @Column
    public String opponent;
    
    @Column (name="datetime")
    public LocalDateTime gameDateTime;
    
    
    public ArrayList<String> playersGame = new ArrayList<String>();
    public ArrayList<Integer> playersPoints = new ArrayList<Integer>();
    
    @Column (name="gamestadium")
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
