package com.devexercise.developer.Service;

import com.devexercise.developer.Entity.Product;
import com.devexercise.developer.Entity.Promotion;
import com.devexercise.developer.Enum.PromotionTypeEnum;
import com.devexercise.developer.Repository.ProductRepository;
import com.devexercise.developer.Repository.PromotionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class PromotionServiceTest {

    private MockMvc mvc;
    private PromotionService promotionService;
    private PromotionRepository promotionRepository;

    @BeforeEach
    void setUp(){
        promotionRepository = mock(PromotionRepository.class);
        promotionService = new PromotionService(promotionRepository);
        mvc = MockMvcBuilders.standaloneSetup(promotionService).build();
    }

    @Test
    void testCreatePromoSuccess() {
        Promotion promotion = new Promotion();
        promotion.setProductName("apple");
        promotion.setPromotionTypeEnum(PromotionTypeEnum.THREE_FOR_TWO);

        when(promotionRepository.save(promotion)).thenReturn(promotion);

        Promotion result = promotionService.createPromotion(promotion);

        assertNotNull(result,"Result shouldnt be null");
        assertEquals("apple",result.getProductName());
        assertEquals(PromotionTypeEnum.THREE_FOR_TWO,result.getPromotionTypeEnum());
        verify(promotionRepository,times(1)).save(promotion);
    }

}