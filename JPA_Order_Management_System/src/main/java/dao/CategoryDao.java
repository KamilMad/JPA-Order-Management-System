package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import model.Category;

import java.util.List;

public class CategoryDao {

    private final EntityManagerFactory emf;


    public CategoryDao(EntityManagerFactory emf) {
        this.emf = emf;
    }


    public void save(Category category) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(category);
            em.getTransaction().commit();
        }
    }

    public Category findById(long id) {
        try(EntityManager em = emf.createEntityManager()) {
            return em.find(Category.class, id);
        }
    }

    public List<Category> findAll() {
        try(EntityManager em = emf.createEntityManager()) {
            String jpql = "SELECT c FROM Category c";
            TypedQuery<Category> q = em.createQuery(jpql, Category.class);
            return q.getResultList();
        }
    }

    public void deleteById(long id){
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Category c = em.find(Category.class, id);
            if (c != null){
                em.remove(c);
            }
            em.getTransaction().commit();
        }
    }

    public void update(Category category){
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.merge(category);
            em.getTransaction().commit();
        }
    }
}
