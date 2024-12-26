package org.example;

import config.HibernateConfig;
import dao.CustomerDao;
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
        properties.put("hibernate.hbm2ddl.auto", "none");

        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new HibernateConfig(), properties);

        CustomerDao customerDao = new CustomerDao(emf);

        emf.close();
    }
}