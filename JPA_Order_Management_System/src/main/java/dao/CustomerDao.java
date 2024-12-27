package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import model.Customer;

import java.util.ArrayList;
import java.util.List;

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

    public double calculateTotalSpending(long id) {
        try(EntityManager em = emf.createEntityManager()){
            String jpql = """ 
                    SELECT SUM(o.totalAmount) FROM Order o JOIN o.customer c WHERE c.id = :id""";

            TypedQuery<Double> q = em.createQuery(jpql, Double.class);
            q.setParameter("id", id);
            Double result =  q.getSingleResult();
            return result != null ? result : 0.0;
        }
    }

}
