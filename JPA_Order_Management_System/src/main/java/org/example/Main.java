package org.example;

import config.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import model.Category;
import model.Customer;
import model.Order;
import model.Product;
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

            Category c1 = new Category();
            c1.setName("Electronics");

            Category c2 = new Category();
            c2.setName("Food");

            Product p1 = new Product();
            p1.setPrice(5.0);
            p1.setName("TV");

            Product p2 = new Product();
            p2.setPrice(10.0);
            p2.setName("Phone");

            Product p3 = new Product();
            p3.setName("Bread");
            p3.setPrice(10.0);

            c1.setProducts(List.of(p1,p2));
            c2.setProducts(List.of(p3));

            p1.setCategory(c1);
            p2.setCategory(c1);
            p3.setCategory(c2);

            em.persist(c1);
            em.persist(c2);
            em.persist(p1);
            em.persist(p2);
            em.persist(p3);
            em.getTransaction().commit();
        }


        System.out.println("EntityManagerFactory created: " + emf.isOpen());
        emf.close();
    }
}