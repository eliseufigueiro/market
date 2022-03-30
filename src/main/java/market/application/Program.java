package market.application;

import market.connection.JpaConnectionFactory;
import market.model.persistence.Category;
import market.model.persistence.Product;
import market.services.CategoryService;
import market.services.ProductService;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class Program {

    public static void main(String[] args) {
        EntityManager entityManager = new JpaConnectionFactory().getEntityManager();
        ProductService productService = new ProductService(entityManager);
        CategoryService categoryService = new CategoryService(entityManager);

        //Add Product
        Product product = new Product("Apple MacBook", "Pro 13p", new BigDecimal(12000.0), new Category("Elite"));
        productService.create(product);

        //Delete Product
        //productService.delete(2L);

        //Find Category
        //Category category = categoryService.findByName("Gamer");
        //System.out.println(category);
    }
}
