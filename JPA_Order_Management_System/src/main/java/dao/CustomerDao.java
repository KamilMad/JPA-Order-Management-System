package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import model.Customer;

import java.util.List;

public class CustomerDao {
    private final EntityManagerFactory emf;


    public CustomerDao(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void save(Customer customer) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
        }
    }

    public Customer findById(long id) {
        Customer customer;
        try(EntityManager em = emf.createEntityManager()){
            String jpql = "SELECT c FROM Customer c WHERE c.id = :id";
            TypedQuery<Customer> q = em.createQuery(jpql, Customer.class);
            q.setParameter("id", id);

            return q.getSingleResult();
        }catch (NoResultException e) {
            System.out.println("Customer with id " + id + " not found");
            return null;
        }
    }

    public List<Customer> findAll() {
        try(EntityManager em = emf.createEntityManager()) {
            String jpql = "SELECT c FROM Customer c";
            TypedQuery<Customer> q = em.createQuery(jpql, Customer.class);
            return q.getResultList();
        }
    }

}
