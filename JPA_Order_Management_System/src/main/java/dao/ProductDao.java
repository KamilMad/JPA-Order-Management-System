package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import model.Product;

public class ProductDao extends CrudDao<Product>{

    private final EntityManagerFactory emf;

    public ProductDao(EntityManagerFactory emf) {
        super(Product.class, emf);
        this.emf = emf;
    }

    public void updatePrice(long id, double price) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();

            Product product = em.find(Product.class, id);

            if (product != null){
                product.setPrice(price);
            } else {
                throw new IllegalArgumentException("Product not found for id: " + id);
            }
            em.getTransaction().commit();
        }
    }
}