/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author atoutsios
 */
@Entity
@Table (name="playertraining")
public class PlayerTraining implements Serializable{
    
    
    @Column(name = "idplayer")
    @ManyToOne
    @Id private Player playerId;
    
    @Column(name = "idtraining")
    @ManyToOne
    @Id private int trainingId;
    
    @Column (name="playerrank")
    private double rank;

    public Player getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Player playerId) {
        this.playerId = playerId;
    }

    public int getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(int trainingId) {
        this.trainingId = trainingId;
    }

    public double getRank() {
        return rank;
    }

    public void setRank(double rank) {
        this.rank = rank;
    }

    public PlayerTraining(Player playerId, int trainingId, double rank) {
        this.playerId = playerId;
        this.trainingId = trainingId;
        this.rank = rank;
    }

    
}
