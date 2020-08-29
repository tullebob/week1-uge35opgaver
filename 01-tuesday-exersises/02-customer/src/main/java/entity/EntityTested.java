
package entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;


public class EntityTested {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        try{
            Customer c1 = new Customer("Jens", "Jensen", new Date());
            Customer c2 = new Customer("Jackie", "Jackiesen", new Date());
            Customer c3 = new Customer("Ole", "Olesen", new Date());
            
            em.getTransaction().begin();
            em.persist(c1);
            em.persist(c2);
            em.persist(c3);
            em.getTransaction().commit();
            
        } finally {
            em.close();
        }
    }
}
