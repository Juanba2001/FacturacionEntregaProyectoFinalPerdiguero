package edu.coderhouse.jpa.services;

import edu.coderhouse.jpa.entities.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {
    Product saveProduct(Product product);
    Product getProductById(Integer id);
    List<Product> getAllProducts();
    Product updateProduct(Integer id, Product product);
    Product partialUpdateProduct(Integer id, Map<String, Object> updates);
    void deleteProduct(Integer id);
}
