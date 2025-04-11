package com.devexercise.developer.Controller;

import com.devexercise.developer.Entity.Promotion;
import com.devexercise.developer.Service.PromotionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/promotions")
public class PurchaseController {
    PromotionService promotionService;

    public PurchaseController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @GetMapping("")
    public ResponseEntity<Promotion> createPromotion(@RequestBody Promotion promotion) {
        return new ResponseEntity<>(promotionService.createPromotion(promotion), HttpStatus.CREATED);
    }
}
