package com.devexercise.developer.Service;

import com.devexercise.developer.Entity.Promotion;
import com.devexercise.developer.Exception.Exceptions.IllegalArgumentException;
import com.devexercise.developer.Repository.PromotionRepository;
import org.apache.commons.lang3.StringUtils;
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
        if(!StringUtils.isNotEmpty(promotion.getProductName())) {
            throw new IllegalArgumentException("productName isn't valid one");
        }
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
