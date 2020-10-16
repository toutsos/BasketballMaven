/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author a.toutsios
 */
@Entity
@Table (name="training")
@NamedQueries({
    @NamedQuery(name = "Training.findAll", query = "SELECT t FROM Training t"),
    @NamedQuery(name = "Training.findFromDate", query = "SELECT t FROM Training t WHERE t.trainingDateTime = :date")
})
public class Training implements Serializable{
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    
    @OneToMany(mappedBy = "trainingId")
        private Set<PlayerTraining> trainingPlayers = new HashSet<PlayerTraining>();
    
    
    @Column(name="DateTimeTraining")
    LocalDateTime trainingDateTime;
    
    @ManyToOne
    private Stadium trainingStadium;
     
    @Transient        
    ArrayList<Player> playersTraining = new ArrayList<Player>();
    @Transient
    ArrayList<Double> playersRanking = new ArrayList<Double>();
    

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
    

    public ArrayList<Double> getPlayersRanking() {
        return playersRanking;
    }

    public void setPlayersRanking(ArrayList<Double> playersRanking) {
        this.playersRanking = playersRanking;
    }
    
     public Stadium getTrainingStadium() {
        return trainingStadium;
    }

    public void setTrainingStadium(Stadium trainingStadium) {
        this.trainingStadium = trainingStadium;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<PlayerTraining> getTrainingPlayers() {
        return trainingPlayers;
    }

    public void setTrainingPlayers(Set<PlayerTraining> trainingPlayers) {
        this.trainingPlayers = trainingPlayers;
    }
    
        public ArrayList<Player> getPlayersTraining() {
        return playersTraining;
    }

    public void setPlayersTraining(ArrayList<Player> playersTraining) {
        this.playersTraining = playersTraining;
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

    @Override
    public String toString() {
        return "Training{" + "Date/Time: " + trainingDateTime + ", Stadium: " + trainingStadium + '}';
    }


   
    
   
}//class
