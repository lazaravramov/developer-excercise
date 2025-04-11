package com.devexercise.developer.Controller;

import com.devexercise.developer.Service.PurchaseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PurchaseControllerTest {

   private MockMvc mvc;
   private PurchaseService purchaseService;
   private PurchaseController purchaseController;

   @BeforeEach
    void setUp(){
       purchaseService = mock(PurchaseService.class);
       purchaseController = new PurchaseController(purchaseService);
       mvc = MockMvcBuilders.standaloneSetup(purchaseController).build();
   }

    @Test
    void testCalculateBasketSuccess() {
        List<String> basket = List.of("apple", "banana", "banana", "potato", "tomato", "banana", "potato");
        String expectedResult = "1.99 aws";

        when(purchaseService.calculateBasket(basket)).thenReturn(expectedResult);

        ResponseEntity<String> response = purchaseController.calculateBasket(basket);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("1.99 aws", response.getBody());

        verify(purchaseService).calculateBasket(basket);
    }



}