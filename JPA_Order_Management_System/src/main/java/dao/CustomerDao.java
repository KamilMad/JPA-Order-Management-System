package dao;

import jakarta.persistence.EntityManagerFactory;
import model.Customer;

public class CustomerDao extends CrudDao<Customer>{

    private final EntityManagerFactory emf;

    public CustomerDao(EntityManagerFactory emf) {
        super(Customer.class, emf);
        this.emf = emf;
    }


}
