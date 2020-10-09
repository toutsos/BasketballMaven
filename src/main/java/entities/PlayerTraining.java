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
import javax.persistence.Table;

/**
 *
 * @author atoutsios
 */
@Entity
@Table (name="playertraining")
public class PlayerTraining implements Serializable{
    
    
    @Column(name = "idplayer")
    @Id private int playerId;
    
    @Column(name = "idtraining")
    @Id private int trainingId;
    @Column (name="playerrank")
    private int rank;

    //CONSTRUCTOR
    public PlayerTraining(int playerId, int trainingId, int rank) {
        this.playerId = playerId;
        this.trainingId = trainingId;
        this.rank = rank;
    }
    
    //GETTERS-SETTERS
    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(int trainingId) {
        this.trainingId = trainingId;
    }
    
    
}
