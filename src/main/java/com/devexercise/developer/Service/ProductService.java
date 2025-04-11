package com.devexercise.developer.Service;

import com.devexercise.developer.Entity.Product;
import com.devexercise.developer.Entity.Promotion;
import com.devexercise.developer.Repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public HashMap<String,Integer> storeProductsInHashMap() {
        HashMap<String,Integer> result = new HashMap<>();
        List<Product> products =getAllProducts();
        for(Product product:    products) {
            result.put(product.getName(),product.getPrice());
        }
        return result;
    }
}
