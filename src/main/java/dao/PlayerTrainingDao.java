/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.PlayerTraining;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author atoutsios
 */
public class PlayerTrainingDao extends JPAUtil<PlayerTraining> {
    
    
        public List<PlayerTraining> findAll(){
        return super.findAll("from basketball.player");
    }
    
    public List<PlayerTraining> findAllPlayers(){
        EntityManager em = getEntityManager();
        TypedQuery query = em.createNamedQuery("PlayerTraining.findAll", PlayerTraining.class);
        List<PlayerTraining> players = query.getResultList();
        return players;
    }
    
    
    public PlayerTraining find(int id){
        return super.find(PlayerTraining.class, id);
    }
    
    public PlayerTraining getPlayerWithoutClosingEm(int id){
        EntityManager em = getEntityManager();
        PlayerTraining t = em.find(PlayerTraining.class, id);
        return t;
    }
    
    public PlayerTraining save(PlayerTraining c){
        return super.save(c);
    }
    
    public PlayerTraining update(PlayerTraining c){
        
        return super.update(c);
    }

    public void delete (int id){
        super.delete(PlayerTraining.class,id);
    }
    
    
    
}
