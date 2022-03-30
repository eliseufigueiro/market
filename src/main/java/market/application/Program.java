package market.application;

import market.connection.JpaConnectionFactory;
import market.model.persistence.Category;
import market.model.persistence.Product;
import market.services.ProductService;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class Program {

    public static void main(String[] args) {
        EntityManager entityManager = new JpaConnectionFactory().getEntityManager();
        ProductService productService = new ProductService(entityManager);

        //Add Product
        //Product product = new Product("Laptop", "Lenovo 14 pol.", new BigDecimal(5999.0), new Category("Gamer"));
        //productService.create(product);

        //Delete Product
        productService.delete(1L);
    }
}
