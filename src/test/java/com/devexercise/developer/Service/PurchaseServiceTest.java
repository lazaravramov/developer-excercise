package com.devexercise.developer.Service;

import com.devexercise.developer.Entity.Product;
import com.devexercise.developer.Exception.Exceptions.NotFoundException;
import com.devexercise.developer.Repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class PurchaseServiceTest {

    private MockMvc mvc;
    private ProductService productService;
    private PromotionService promotionService;

    private PurchaseService purchaseService;

    @BeforeEach
    void setUp(){
        productService = mock(ProductService.class);
        promotionService=mock(PromotionService.class);
        purchaseService = new PurchaseService(productService,promotionService);
        mvc = MockMvcBuilders.standaloneSetup(purchaseService).build();
    }

    @Test
    void testCalculateBasketSuccess() {
        List<String> basket = List.of("apple", "banana", "banana", "potato", "tomato", "banana", "potato");

        HashMap<String, String> mockPromotions = new HashMap<>();
        mockPromotions.put("apple", "THREE_FOR_TWO");
        mockPromotions.put("tomato", "THREE_FOR_TWO");
        mockPromotions.put("banana", "THREE_FOR_TWO");
        mockPromotions.put("potato", "TWO_FOR_ONE_AND_HALF");

        HashMap<String, Integer> mockProducts = new HashMap<>();
        mockProducts.put("apple", 50);
        mockProducts.put("banana", 40);
        mockProducts.put("potato", 26);
        mockProducts.put("tomato", 30);

        when(promotionService.storePromotionsInHashMap()).thenReturn(mockPromotions);
        when(productService.storeProductsInHashMap()).thenReturn(mockProducts);

        String result = purchaseService.calculateBasket(basket);

        assertEquals("1.99 aws", result);
    }

    @Test
    void TestCalculateBasketWithNonexistentProduct() {
        List<String> basket = List.of("apple", "banana");

        HashMap<String, String> mockPromotions = new HashMap<>();
        mockPromotions.put("apple", "THREE_FOR_TWO");

        HashMap<String, Integer> mockProducts = new HashMap<>();
        mockProducts.put("apple", 50);

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            purchaseService.calculateBasket(basket);
        });

        assertEquals("There is not a product with such name in the db", exception.getDetail());

    }

}