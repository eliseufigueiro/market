package market.services;

import market.model.DAO.CategoryDAO;
import market.model.persistence.Category;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

public class CategoryService {

    private final Logger LOG = LogManager.getLogger(CategoryService.class);

    private EntityManager entityManager;

    private CategoryDAO categoryDAO;

    public CategoryService(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.categoryDAO = new CategoryDAO(entityManager);
    }

    private void getBeginTransaction() {
        this.LOG.info("Abrindo Transação com o Banco de Dados!");
        entityManager.getTransaction().begin();
    }

    private void commitAndCloseTransaction() {
        this.LOG.info("Commitando e Fechando Transação com o Banco de Dados!");
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Category findByName(String name) {
        if (name == null || name.isEmpty()) {
            this.LOG.error("O Nome não pode ser Nulo!");
            throw new RuntimeException("The Name is Null!");
        }
        try {
            return this.categoryDAO.findByName(name.toLowerCase());
        } catch (NoResultException e) {
            this.LOG.info("Não foi encontrada Categoria, será Criada!");
            return null;
        }
    }

    public void delete(Long id) {
        //TODO: Implementar delete,
    }

    public Category getById(Long id) {
        if (id == null) {
            this.LOG.info("O ID está Nulo!");
            throw new RuntimeException("The Parameter is Null!");
        }

        Category category = this.categoryDAO.getById(id);

        if (category == null) {
            this.LOG.info("Não foi encontrada a Categoria de ID: " + id);
            throw new EntityNotFoundException("Category Not Found!");
        }
        return category;
    }
}
