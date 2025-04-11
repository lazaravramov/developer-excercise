package com.devexercise.developer.Controller;

import com.devexercise.developer.Entity.Product;
import com.devexercise.developer.Entity.Promotion;
import com.devexercise.developer.Service.PromotionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/promotions")
public class PromotionController {

    PromotionService promotionService;

    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @PostMapping("")
    public ResponseEntity<Promotion> createPromotion(@Valid @RequestBody Promotion promotion) {
        return new ResponseEntity<>(promotionService.createPromotion(promotion), HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<Promotion>> getAllPromotions() {
        return  new ResponseEntity<>(promotionService.getAllPromotions(),HttpStatus.OK);
    }
}
