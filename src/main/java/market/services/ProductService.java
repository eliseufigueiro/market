package market.services;

import market.model.DAO.ProductDAO;
import market.model.persistence.Category;
import market.model.persistence.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

public class ProductService {

    private final Logger LOG = LogManager.getLogger(ProductService.class);

    private EntityManager entityManager;

    private ProductDAO productDAO;

    private CategoryService categoryService;

    public ProductService(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.productDAO = new ProductDAO(entityManager);
        this.categoryService = new CategoryService(entityManager);
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

    public void create(Product product) {
        if (product == null) {
            this.LOG.error("O Produto informado está Nulo!");
            throw new RuntimeException("The Product is Null!");
        }

        String categoryName = product.getCategory().getName();
        this.LOG.info("Buscando se já existe a Categoria: " + categoryName);
        Category category = this.categoryService.findByName(categoryName);

        if (category != null) {
            product.setCategory(category);
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

    public void delete(Long id) {
        this.LOG.info("Preparando para Buscar o Produto!");
        if (id == null) {
            this.LOG.error("O ID do Produto informado está Nulo!");
            throw new RuntimeException("The ID is Null!");
        }

        Product product = this.productDAO.getById(id);

        if (product == null) {
            this.LOG.error("O Produto não Existe!");
            throw new EntityNotFoundException("Product not Found!");
        }

        this.LOG.info("Produto Encontrado com Sucesso!");

        getBeginTransaction();
        this.productDAO.delete(product);
        commitAndCloseTransaction();
        this.LOG.info("Produto Deletado com Sucesso!");
    }
}
