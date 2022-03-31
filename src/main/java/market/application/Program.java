package market.application;

import market.connection.JpaConnectionFactory;
import market.model.persistence.Category;
import market.model.persistence.Product;
import market.services.CategoryService;
import market.services.ProductService;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class Program {

    public static void main(String[] args) {
        EntityManager entityManager = new JpaConnectionFactory().getEntityManager();
        ProductService productService = new ProductService(entityManager);
        CategoryService categoryService = new CategoryService(entityManager);

        //Add Product
        //Product product = new Product("Apple MacBook", "Pro 13p", new BigDecimal(13000.0), new Category("Elite"));
        //productService.create(product);

        //Delete Product
        //productService.delete(2L);

        //Find Category
        //Category category = categoryService.findByName("Gamer");
        //System.out.println(category);

        //Update Product
        //productService.update(product, 7L);

        //Listar Products
        //List<Product> productList = productService.listAll();
        //productList.stream().forEach(p -> System.out.println(p));

        //Listar Products Name
        //List<Product> productList = productService.listByName("MacBook");
        //productList.stream().forEach(p -> System.out.println(p));

        //Get Category
        categoryService.getById(5L);
    }
}
