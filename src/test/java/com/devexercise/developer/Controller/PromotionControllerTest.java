package com.devexercise.developer.Controller;

import com.devexercise.developer.Entity.Promotion;
import com.devexercise.developer.Enum.PromotionTypeEnum;
import com.devexercise.developer.Service.PromotionService;
import com.devexercise.developer.Service.PurchaseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PromotionControllerTest {
    private MockMvc mvc;
    private PromotionService promotionService;
    private PromotionController promotionController;

    @BeforeEach
    void setUp(){
        promotionService = mock(PromotionService.class);
        promotionController = new PromotionController(promotionService);
        mvc = MockMvcBuilders.standaloneSetup(promotionController).build();
    }

    @Test
    void testCreatePromotionSuccess() throws Exception {
        String validPromotionJson = """
            {
              "productName": "apple",
              "promotionTypeEnum": "THREE_FOR_TWO"
            }
        """;

        Promotion promotion = new Promotion();
        promotion.setProductName("apple");
        promotion.setPromotionTypeEnum(PromotionTypeEnum.THREE_FOR_TWO);

        when(promotionService.createPromotion(any(Promotion.class))).thenReturn(promotion);

        mvc.perform(post("/promotions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validPromotionJson))
                .andExpect(status().isCreated())  // 201 OK
                .andExpect(jsonPath("$.productName").value("apple"))
                .andExpect(jsonPath("$.promotionTypeEnum").value("THREE_FOR_TWO"));

        verify(promotionService).createPromotion(any(Promotion.class)); // Проверяваме дали промоцията е създадена
    }

    @Test
    void testCreatePromotionInvalidInput_returnsBadRequest() throws Exception {
        String invalidPromotionJson = """
            {
              "productName": "",
              "promotionTypeEnum": "THREE_FOR_TWO"
            }
        """;

        mvc.perform(post("/promotions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidPromotionJson))
                .andExpect(status().isBadRequest());
    }

}