/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author atoutsios
 */
@Embeddable
public class PlayerTrainingPK implements Serializable {
    
    @Column (name="playerId_id")
    private int player_id;
    
    @Column (name="trainingId_id")
    private int training_id;
    
}
