package market.model.DAO;

import javax.persistence.EntityManager;

public class ProductDAO {

    private EntityManager entityManager;

    public ProductDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
