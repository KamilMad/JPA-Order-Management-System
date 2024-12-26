package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import model.Customer;
import model.Order;

import java.util.List;

public class OrderDao extends CrudDao<Order>{

    private final EntityManagerFactory emf;
    public OrderDao(EntityManagerFactory emf) {
        super(Order.class, emf);
        this.emf = emf;
    }

    public List<Order> findAllOrdersForCustomer(Customer customer){
        try(EntityManager em = emf.createEntityManager()){
            String jpql = """
                    SELECT o
                    FROM Order o
                    WHERE o.customer.id = :customerId
                    """;

            TypedQuery<Order> q = em.createQuery(jpql, Order.class);
            q.setParameter("customerId", customer.getId());
            
            return q.getResultList();
        }
    }
}
