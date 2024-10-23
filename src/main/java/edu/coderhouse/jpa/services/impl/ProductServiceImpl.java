package edu.coderhouse.jpa.services.impl;

import edu.coderhouse.jpa.entities.Product;
import edu.coderhouse.jpa.repositories.ProductRepository;
import edu.coderhouse.jpa.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
    @Override
    public Product getProductById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    @Override
    public Product updateProduct(Integer id, Product product) {
        Product existingProduct = productRepository.findById(id).orElse(null);
        if (existingProduct != null) {
            existingProduct.setDescription(product.getDescription());
            existingProduct.setCode(product.getCode());
            existingProduct.setStock(product.getStock());
            existingProduct.setPrice(product.getPrice());
            return productRepository.save(existingProduct);
        }
        return null;
    }
    @Override
    public Product partialUpdateProduct(Integer id, Map<String, Object> updates) {
        Product existingProduct = productRepository.findById(id).orElse(null);
        if (existingProduct != null) {
            updates.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Product.class, key);
                if (field != null) {
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, existingProduct, value);
                }
            });
            return productRepository.save(existingProduct);
        }
        return null;
    }
    @Override
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }
}
