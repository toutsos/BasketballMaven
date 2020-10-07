/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author a.toutsios
 */
@Entity
@Table (name="training")
public class Training implements Serializable{
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
            
    ArrayList<Player> playersTraining = new ArrayList<Player>();
    ArrayList<Double> playersRanking = new ArrayList<Double>();
    
    @Column(name="datetime")
    LocalDateTime trainingDateTime;
    
    @Column (name="trainingstadium")
    protected Stadium trainingStadium;

    //Constructors//
    
    public Training(LocalDateTime date, Stadium stadium) {
        this.trainingDateTime = date;
        this.trainingStadium=stadium;
    }



    public Training() {
    }
    
    //Getters - Setters//

    
    public LocalDateTime getTrainingDateTime() {
        return trainingDateTime;
    }

    public void setTrainingDateTime(LocalDateTime trainingDateTime) {
        this.trainingDateTime = trainingDateTime;
    }
    
    public ArrayList<Player> getPlayersTraining() {
        return playersTraining;
    }

    public void setPlayersTraining(ArrayList<Player> playersTraining) {
        this.playersTraining = playersTraining;
    }

    public ArrayList<Double> getPlayersRanking() {
        return playersRanking;
    }

    public void setPlayersRanking(ArrayList<Double> playersRanking) {
        this.playersRanking = playersRanking;
    }
  
    //METHODS//
    
    public void addPlayer(Player x){
        playersTraining.add(x);
    }
    
    public void addRank(double x){
        playersRanking.add(x);
    } 
    
    public void showTraining () {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Our training: " +trainingDateTime+" ,Stadium: "+trainingStadium.getName());
        System.out.println("Players that participated:");
        for (int i=0; i<playersTraining.size(); i++){
            System.out.println("Name: "+playersTraining.get(i).getName()+" Rank: "+playersRanking.get(i));
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }//showTraining
   
}//class
