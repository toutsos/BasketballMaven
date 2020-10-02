/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import entities.Coach;
import java.time.LocalDate;
/**
 *
 * @author a.toutsios
 */
public class CoachDao{
    
    public void insertCoach(){
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        LocalDate date = LocalDate.parse("2020-01-01");
        Coach newCoach = new Coach("Santos",50000,date);
        em.persist(newCoach);
        em.getTransaction().commit();
        em.close();
    }
    
}
