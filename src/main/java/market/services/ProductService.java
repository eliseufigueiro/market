package market.services;

import market.model.DAO.ProductDAO;
import market.model.persistence.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;

public class ProductService {

    private final Logger LOG = LogManager.getLogger(ProductService.class);

    private EntityManager entityManager;

    private ProductDAO productDAO;

    public ProductService(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.productDAO = new ProductDAO(entityManager);
    }

    public void create(Product product) {
        if (product == null) {
            this.LOG.error("O Produto informado está Nulo!");
            throw new RuntimeException("O Produto está Nulo!");
        }
        try {
            this.LOG.info("Preparando para a Criação de um Produto!");
            getBeginTransaction();
            this.productDAO.create(product);
            commitAndCloseTransaction();
        } catch (Exception e) {
            this.LOG.error("Erro ao criar um produto, causado por: " + e.getMessage());
            throw new RuntimeException(e);
        } finally {
            this.LOG.info("Produto Criado com Sucesso!");
        }
    }

    private void commitAndCloseTransaction() {
        this.LOG.info("Commitando e Fechando Transação com o Banco de Dados!");
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private void getBeginTransaction() {
        this.LOG.info("Abrindo Transação com o Banco de Dados!");
        entityManager.getTransaction().begin();
    }
}
