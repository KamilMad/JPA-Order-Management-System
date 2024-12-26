package dao;

import jakarta.persistence.EntityManagerFactory;
import model.Category;

public class CategoryDao extends CrudDao<Category>{

    public CategoryDao(EntityManagerFactory emf) {
        super(Category.class, emf);
    }
}
