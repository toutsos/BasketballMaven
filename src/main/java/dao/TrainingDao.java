/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Training;
import java.util.List;
import javax.persistence.EntityManager;
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
    
}//class
