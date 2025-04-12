package com.devexercise.developer.Service;

import com.devexercise.developer.Entity.Product;
import com.devexercise.developer.Entity.Promotion;
import com.devexercise.developer.Exception.Exceptions.IllegalArgumentException;
import com.devexercise.developer.Repository.ProductRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        if(!StringUtils.isNotEmpty(product.getName())) {
            throw new IllegalArgumentException("Product name isn't valid one");
        }
        if(product.getPrice() <= 0) {
            throw  new IllegalArgumentException("Price isn't valid one") ;
        }
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
