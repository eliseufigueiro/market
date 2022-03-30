package market.services;

import market.model.DAO.CategoryDAO;
import market.model.persistence.Category;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class CategoryService {

    private final Logger LOG = LogManager.getLogger(CategoryService.class);

    private EntityManager entityManager;

    private CategoryDAO categoryDAO;

    public CategoryService(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.categoryDAO = new CategoryDAO(entityManager);
    }

    public Category findByName(String name) {
        if (name == null || name.isEmpty()) {
            this.LOG.error("O Nome não pode ser Nulo!");
            throw new RuntimeException("The Name is Null!");
        }
        try {
            return this.categoryDAO.findByName(name.toLowerCase());
        } catch (NoResultException e){
            this.LOG.info("Não foi encontrada Categoria, será Criada!");
         return null;
        }
    }
}
