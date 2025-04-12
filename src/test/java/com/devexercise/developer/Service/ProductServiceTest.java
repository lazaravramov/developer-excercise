package com.devexercise.developer.Service;

import com.devexercise.developer.Controller.ProductController;
import com.devexercise.developer.Entity.Product;
import com.devexercise.developer.Repository.ProductRepository;
import com.devexercise.developer.Exception.Exceptions.IllegalArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    private MockMvc mvc;
    private ProductService productService;
    private ProductRepository productRepository;

    @BeforeEach
    void setUp(){
        productRepository = mock(ProductRepository.class);
        productService = new ProductService(productRepository);
        mvc = MockMvcBuilders.standaloneSetup(productService).build();
    }

    @Test
    void testCreateProduct() {
        Product product = new Product();
        product.setPrice(10);
        product.setName("grape");

        when(productRepository.save(product)).thenReturn(product);

        Product result = productService.createProduct(product);

        assertNotNull(result,"Result shouldnt be null");
        assertEquals("grape",result.getName());
        assertEquals(10,result.getPrice());
        verify(productRepository,times(1)).save(product);
    }

    @Test
    void testCreateProductWithEmptyName() {
        Product product = new Product();
        product.setPrice(10);
        product.setName("");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            productService.createProduct(product);
        });
        assertEquals("Product name isn't valid one", exception.getDetail());
    }

}