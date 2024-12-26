package dao;

import jakarta.persistence.EntityManagerFactory;
import model.Customer;

public class CustomerDao extends CrudDao<Customer>{


    public CustomerDao(EntityManagerFactory emf) {
        super(Customer.class, emf);
    }

}
