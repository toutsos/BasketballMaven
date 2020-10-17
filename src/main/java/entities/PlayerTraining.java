/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author atoutsios
 */
@Entity
@Table (name="player_training")
public class PlayerTraining implements Serializable{
    
    @Id
    private int id;
    
    @Column (name="playerRank")
    private int rank;
    
    @ManyToOne
    @JoinColumn(name="playerId")
    private Player player;
    
    @ManyToOne
    @JoinColumn (name="trainingId")
    private Training training;

    public PlayerTraining(int rank, Player player, Training training) {
        this.rank = rank;
        this.player = player;
        this.training = training;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }
    
    
    
    
  
    
}
