/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import entities.Player;
import entities.PlayerTraining;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.hibernate.Session;

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
    
    public String getState(Player p){
        boolean isManaged = getEntityManager().contains(p);
        return (isManaged?"Managed":"Detached");//ternary operation
    }
    
    
    
    public void updatePlayer(Player p){
       EntityManager em = getEntityManager();
       em.getTransaction().begin();
       Player old = em.find(Player.class, p.getId());
       old.setTotalRank(p.getTotalRank());
       old.setTotalTrainings(p.getTotalTrainings()+1);
//       old.setTrainings(p.getTrainings());
       
       old = em.merge(old);
       em.getTransaction().commit();
       em.close();
    }
    
//    public Player updateTrainings(Player c){
//        EntityManager em = getEmf().createEntityManager();
//        em.getTransaction().begin();
//        c.setTrainings(c.getTrainings()+1);
//        em.getTransaction().commit();
//        closeEntityManager();
//        return c;
//    }
    
//    public void updateTraining(Player c){
//        EntityManager em = getEmf().createEntityManager();
//        em.getTransaction().begin();
//        Player old = find(c.getId());
//        old.trainings++;
//        old.setPlayerTrainings(c.getPlayerTrainings());
//        em.getTransaction().commit();
//        closeEntityManager();
//    }

    public void delete (int id){
        super.delete(Player.class,id);
    }
    
}//class
