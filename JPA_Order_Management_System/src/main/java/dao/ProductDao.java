package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Tuple;
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
                    WITH ProductPopularity AS (
                        SELECT p.id, p.name, COUNT(op.order_id) AS order_count
                        FROM orders_product op
                        JOIN product p ON op.product_id = p.id
                        GROUP BY p.id, p.name
                    )
                    SELECT id, name, order_count
                    FROM ProductPopularity
                    WHERE order_count = (
                        SELECT MAX(order_count) FROM ProductPopularity
                    )
                    """;

            TypedQuery<Product> q = em.createQuery(jpql, Product.class);
            q.setParameter("category", category);
            q.setParameter("price", price);
            return q.getResultList();
        }
    }

    public void getMostPopularProduct(){
        try(EntityManager em = emf.createEntityManager()) {

            String sql = """
                WITH ProductPopularity AS (
                    SELECT p.id, p.name, COUNT(op.order_id) AS order_count
                    FROM orders_product op
                    JOIN product p ON op.product_id = p.id
                    GROUP BY p.id, p.name
                )
                SELECT id, name, order_count
                FROM ProductPopularity
                WHERE order_count = (
                    SELECT MAX(order_count) FROM ProductPopularity
                );
            """;

            List<Tuple> results = em.createNativeQuery(sql, Tuple.class).getResultList();
            for (Tuple tuple : results) {
                Long id = tuple.get("id", Long.class);
                String name = tuple.get("name", String.class);
                Long orderCount = tuple.get("order_count", Long.class);

                // Handle the result, e.g., printing the values
                System.out.println("Product ID: " + id + ", Name: " + name + ", Order Count: " + orderCount);
            }

        }
    }
}