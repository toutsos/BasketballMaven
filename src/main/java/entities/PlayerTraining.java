/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

/**
 *
 * @author atoutsios
 */
@Entity
@Table (name="playertraining")
public class PlayerTraining implements Serializable{
    
    @EmbeddedId
    private PlayerTrainingPK id;
    
    @ManyToOne
    @MapsId("player_id")
    @JoinColumn( name="playerId_id")
    private Player playerId;

    @ManyToOne
    @MapsId("training_id")
    @JoinColumn(name="trainingId_id")
    private Training trainingId;
    
    @Column(name="playerrank")
    private double rank;

     public PlayerTraining(Player player,Training trainingId, double rank) {
        this.playerId = player;
        this.trainingId = trainingId;
        this.rank = rank;
    }
    
    public Player getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Player playerId) {
        this.playerId = playerId;
    }

    public Training getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(Training trainingId) {
        this.trainingId = trainingId;
    }

    public double getRank() {
        return rank;
    }

    public void setRank(double rank) {
        this.rank = rank;
    }

   

    
}
