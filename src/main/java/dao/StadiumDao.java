/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Stadium;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
/**
 *
 * @author a.toutsios
 */
public class StadiumDao extends JPAUtil<Stadium> {
    
    public List<Stadium> findAll(){
            return super.findAll("from basketball.stadium");
        }
        public Stadium find(int id){
            return super.find(Stadium.class, id);
        }
        
        public List<Stadium> findAllStadiums(){
            EntityManager em = getEntityManager();
            TypedQuery query = em.createNamedQuery("Stadium.findAll", Stadium.class);
            List<Stadium> list = new ArrayList<>();
            list = query.getResultList();
            return list;
        }
        
        public Stadium getStadiumWithoutClosingEm(int id){
            EntityManager em = getEntityManager();
            Stadium t = em.find(Stadium.class, id);
            return t;
        }
        
        public Stadium save(Stadium c){
            return super.save(c);
        }
        
        public Stadium update(Stadium c){
            return super.update(c);
        }
        
        public void delete (int id){
            super.delete(Stadium.class,id);
        }
    
        public Stadium findStadiumFromName(String name){
            EntityManager em = getEntityManager();
            TypedQuery<Stadium> query = em.createNamedQuery("Stadium.findFromName", Stadium.class);
            query.setParameter("name", name);
            List<Stadium> list = query.getResultList();
            return list.get(0);
        }
        
}
