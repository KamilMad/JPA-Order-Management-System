package org.example;

import config.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import model.Customer;
import model.Order;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.time.LocalDateTime;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Map<String, String> properties = new HashMap<>();
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.hbm2ddl.auto", "create");

        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new HibernateConfig(), properties);

        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            Customer customer = new Customer();
            customer.setName("Joanna");
            customer.setEmail("joanna@gmail");

            Customer customer2 = new Customer();
            customer2.setName("kasia");
            customer2.setEmail("kasia@gmail");

            Order o1 = new Order();
            o1.setOrderDate(LocalDateTime.now());
            o1.setTotalAmount(10.0);

            Order o2 = new Order();
            o2.setOrderDate(LocalDateTime.now());
            o2.setTotalAmount(15.0);

            Order o3 = new Order();
            o3.setOrderDate(LocalDateTime.now());
            o3.setTotalAmount(20.0);

            customer.setOrders(List.of(o1,o2));
            o1.setCustomer(customer);
            o2.setCustomer(customer);
            o3.setCustomer(customer2);

            em.persist(customer);
            em.persist(customer2);
            em.persist(o1);
            em.persist(o2);
            em.persist(o3);

            em.getTransaction().commit();
        }


        System.out.println("EntityManagerFactory created: " + emf.isOpen());
        emf.close();
    }
}