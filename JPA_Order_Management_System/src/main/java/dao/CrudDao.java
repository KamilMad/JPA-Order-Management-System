package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import java.util.List;

public abstract class CrudDao<T> {
    private final Class<T> entityType;
    private final EntityManagerFactory emf;

    public CrudDao(Class<T> entityType, EntityManagerFactory emf) {
        this.entityType = entityType;
        this.emf = emf;
    }


    public void save(T entity) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        }
    }

    public T findById(long id) {
        try(EntityManager em = emf.createEntityManager()) {
            return em.find(entityType, id);
        }
    }

    public List<T> findAll() {
        try(EntityManager em = emf.createEntityManager()) {
            String jpql = "SELECT t FROM " + entityType.getSimpleName() + " t";
            TypedQuery<T> q = em.createQuery(jpql, entityType);
            return q.getResultList();
        }
    }

    public void deleteById(long id){
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            T t = em.find(entityType, id);
            if (t != null){
                em.remove(t);
            }
            em.getTransaction().commit();
        }
    }

    public void update(T t){
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.merge(t);
            em.getTransaction().commit();
        }
    }
}
