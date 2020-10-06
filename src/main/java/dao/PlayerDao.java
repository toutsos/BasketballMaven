/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import entities.Player;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author a.toutsios
 */
public class PlayerDao extends JPAUtil<Player> {
    
    public List<Player> findAll(){
        return super.findAll("from basketball.player");
    }
    public List<Player> findAllPlayers(){
        EntityManager em = getEntityManager();
        TypedQuery query = em.createNamedQuery("Player.findAll", Player.class);
        List<Player> players = query.getResultList();
        return players;
//        return super.findAllPlayers("SELECT a FROM player a");
    }
    
    public Player findPlayerFromName(String name){
        EntityManager em = getEntityManager();
        TypedQuery<Player> query = em.createNamedQuery("Player.findFromName", Player.class);
        query.setParameter("name", name);
        List<Player> list = query.getResultList();
        return list.get(0);
    }
    
    public Player find(int id){
        return super.find(Player.class, id);
    }
    
    public Player getPlayerWithoutClosingEm(int id){
        EntityManager em = getEntityManager();
        Player t = em.find(Player.class, id);
        return t;
    }
    
    public Player save(Player c){
        return super.save(c);
    }
    
    public Player update(Player c){
        return super.update(c);
    }

    public void delete (int id){
        super.delete(Player.class,id);
    }
    
}//class
