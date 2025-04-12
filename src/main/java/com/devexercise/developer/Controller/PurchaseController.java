package com.devexercise.developer.Controller;

import com.devexercise.developer.Entity.Promotion;
import com.devexercise.developer.Exception.Exceptions.IllegalArgumentException;
import com.devexercise.developer.Service.PromotionService;
import com.devexercise.developer.Service.PurchaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {
    PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping("")
    public ResponseEntity<String> calculateBasket(@RequestParam List<String> basket) {
        boolean hasInvalidItem = basket.stream().anyMatch(item -> item == null || item.trim().isEmpty());
        if (hasInvalidItem) {
            throw new IllegalArgumentException("Списъкът с продукти съдържа празни или невалидни стойности.");
        }
        return new ResponseEntity<>(purchaseService.calculateBasket(basket),HttpStatus.OK);
    }
}
