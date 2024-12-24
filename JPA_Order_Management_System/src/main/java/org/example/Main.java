package org.example;

import config.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import model.Customer;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

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
            customer.setName("Ewa");

            em.persist(customer);

            em.getTransaction().commit();
        }


        System.out.println("EntityManagerFactory created: " + emf.isOpen());
        emf.close();
    }
}