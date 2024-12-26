package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import model.Customer;

public class CustomerDao extends CrudDao<Customer>{

    private final EntityManagerFactory emf;

    public CustomerDao(EntityManagerFactory emf) {
        super(Customer.class, emf);
        this.emf = emf;
    }

    public void updateName(long id, String name){
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            Customer customer = em.find(Customer.class, id);
            customer.setName(name);
            em.getTransaction().commit();
        }
    }

    public void updateEmail(long id, String email){
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            Customer customer = em.find(Customer.class, id);
            customer.setName(email);
            em.getTransaction().commit();
        }
    }

}
