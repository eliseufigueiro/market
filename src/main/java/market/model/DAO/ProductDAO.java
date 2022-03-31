package market.model.DAO;

import market.model.persistence.Product;

import javax.persistence.EntityManager;
import java.util.List;

public class ProductDAO {

    private EntityManager entityManager;

    public ProductDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void create(Product product) {
        this.entityManager.persist(product);
    }

    public void delete(Product product) {
        this.entityManager.remove(convertToMerge(product));
    }

    public Product update(Product product) {
        return convertToMerge(product);
    }

    public Product getById(Long id) {
        return this.entityManager.find(Product.class, id);
    }

    public List listAll() {
        String sql = "SELECT * FROM product";
        return this.entityManager.createNativeQuery(sql, Product.class).getResultList();
    }

    public List listByName(String name) {
        String sql = "SELECT * FROM product WHERE name =:name";
        return this.entityManager.createNativeQuery(sql, Product.class).setParameter("name", name).getResultList();
    }

    private Product convertToMerge(Product product) {
        return this.entityManager.merge(product);
    }
}
