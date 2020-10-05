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
import java.util.List;
/**
 *
 * @author a.toutsios
 */
public class CoachDao extends JPAUtil<Coach>{
        
        public List<Coach> findAll(){
            return super.findAll("from coach");
        }
        public Coach find(int id){
            return super.find(Coach.class, id);
        }
        
        public Coach getCustomerWithoutClosingEm(int id){
            EntityManager em = getEntityManager();
            Coach t = em.find(Coach.class, id);
            return t;
        }
        
        public Coach save(Coach c){
            return super.save(c);
        }
        
        public Coach update(Coach c){
            return super.update(c);
        }
        
        public void delete (int id){
            super.delete(Coach.class,id);
        }

}//class
