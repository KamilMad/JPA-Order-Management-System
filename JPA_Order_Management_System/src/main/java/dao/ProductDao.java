package dao;

import jakarta.persistence.EntityManagerFactory;
import model.Product;

public class ProductDao extends CrudDao<Product>{
    public ProductDao(EntityManagerFactory emf) {
        super(Product.class, emf);
    }
}
