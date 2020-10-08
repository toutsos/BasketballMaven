/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Player;
import entities.Training;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
/**
 *
 * @author a.toutsios
 */
public class TrainingDao extends JPAUtil<Training>{
    
    public List<Training> findAll(){
            return super.findAll("from Training");
        }
        public Training find(int id){
            return super.find(Training.class, id);
        }
        
        public Training getTrainingWithoutClosingEm(int id){
            EntityManager em = getEntityManager();
            Training t = em.find(Training.class, id);
            return t;
        }
        
        public Training save(Training c){
            return super.save(c);
        }
        
        public Training update(Training c){
            return super.update(c);
        }
        
        public void delete (int id){
            super.delete(Training.class,id);
        }
        
        public Training findTrainingFromDate(LocalDate date){
        EntityManager em = getEntityManager();
        TypedQuery<Training> query = em.createNamedQuery("Training.findFromDate", Training.class);
        query.setParameter("date", date);
        List<Training> list = query.getResultList();
        return list.get(0);
    }
    
}//class
