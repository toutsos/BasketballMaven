
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil<T> {

    private static EntityManagerFactory emf;
    private EntityManager em;
    
    protected static EntityManagerFactory getEmf() {
        if (emf == null) {
            System.out.println("*******Opening EntityManagerFactory*********");
            emf = Persistence.createEntityManagerFactory("salesPU");
        }
        return emf;
    }
    
    public static void closeEntityManagerFactory() {
        System.out.println("*******Closing EntityManagerFactory*********");
        emf.close();
    }
    
    protected EntityManager getEntityManager() {
        em = getEmf().createEntityManager();
        return em;
    }
    
    protected void closeEntityManager() {
        em.close();
    }
    
    protected T save(T t) {
        getEntityManager();
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
        closeEntityManager();
        return t;
    }
    
    public T update(T t) {
        getEntityManager();
        em.getTransaction().begin();
        t = em.merge(t);
        em.getTransaction().commit();
        closeEntityManager();
        return t;
    }
    
    protected T find(Class<T> type, int id) {
        getEntityManager();
        T t = em.find(type, id);
        closeEntityManager();
        return t;
    }
    
    protected List<T> findAll(String query) {
        getEntityManager();
        List<T> list = em.createQuery(query).getResultList();
        closeEntityManager();
        return list;
    }
    
    public void delete(Class<T> type, Object id) {
        getEntityManager();
        em.getTransaction().begin();
        T t = em.getReference(type, id);
        em.remove(t);
        em.getTransaction().commit();
        em.close();
    }



}//class