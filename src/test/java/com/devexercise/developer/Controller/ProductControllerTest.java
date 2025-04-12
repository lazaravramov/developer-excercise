package com.devexercise.developer.Controller;

import com.devexercise.developer.Entity.Product;
import com.devexercise.developer.Entity.Promotion;
import com.devexercise.developer.Enum.PromotionTypeEnum;
import com.devexercise.developer.Exception.Exceptions.IllegalArgumentException;
import com.devexercise.developer.Service.ProductService;
import com.devexercise.developer.Service.PromotionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {
    private MockMvc mvc;
    private ProductService productService;
    private ProductController   productController;

    @BeforeEach
    void setUp(){
        productService = mock(ProductService.class);
        productController = new ProductController(productService);
        mvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    void testCreateProductSuccess() throws Exception {
        String validPromotionJson = """
            {
              "name": "apple",
              "price": 50
            }
        """;

        Product product = new Product();
        product.setName("apple");
        product.setPrice(50);

        when(productService.createProduct(any(Product.class))).thenReturn(product);

        mvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validPromotionJson))
                .andExpect(status().isCreated())  // 201 OK
                .andExpect(jsonPath("$.name").value("apple"))
                .andExpect(jsonPath("$.price").value(50));

        verify(productService).createProduct(any(Product.class));
    }
}