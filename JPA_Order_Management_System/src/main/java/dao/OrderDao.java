package dao;

import jakarta.persistence.EntityManagerFactory;
import model.Order;

public class OrderDao extends CrudDao<Order>{

    public OrderDao(EntityManagerFactory emf) {
        super(Order.class, emf);    }
}
