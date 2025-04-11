package com.devexercise.developer.Service;

import com.devexercise.developer.Entity.Promotion;
import com.devexercise.developer.Repository.PromotionRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PromotionService {
    private PromotionRepository promotionRepository;

    public PromotionService(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    public List<Promotion> getAllPromotions() {
        return promotionRepository.findAll();
    }

    public Promotion createPromotion(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

    public HashMap<String,String> storePromotionsInHashMap() {
        HashMap<String,String> result = new HashMap<>();
        List<Promotion> promotions =getAllPromotions();
        for(Promotion promotion:promotions) {
            result.put(promotion.getProductName(),promotion.getPromotionTypeEnum().name());
        }
        return result;
    }
}
