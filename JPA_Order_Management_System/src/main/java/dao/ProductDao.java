package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import model.Product;

import java.util.List;

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

    public List<Product> findAllProductsByCategoryWithPriceLowerThanX(String category, double price) {
        try(EntityManager em = emf.createEntityManager()) {
            String jpql = """
                    SELECT p
                    FROM Product p
                    WHERE p.category.name = :category
                    AND p.price < :price
                    """;
            TypedQuery<Product> q = em.createQuery(jpql, Product.class);
            q.setParameter("category", category);
            q.setParameter("price", price);
            return q.getResultList();
        }
    }
}