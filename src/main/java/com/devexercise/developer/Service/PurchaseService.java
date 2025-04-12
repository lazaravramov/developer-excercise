package com.devexercise.developer.Service;

import com.devexercise.developer.Entity.Product;
import com.devexercise.developer.Entity.Promotion;
import com.devexercise.developer.Exception.Exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PurchaseService {
    private ProductService productService;
    private PromotionService promotionService;

    public PurchaseService(ProductService productService, PromotionService promotionService) {
        this.productService = productService;
        this.promotionService = promotionService;
    }

    public String calculateBasket(List<String> basket) {
        HashMap<String,String> promotions = promotionService.storePromotionsInHashMap();
        HashMap<String,Integer> products = productService.storeProductsInHashMap();
        Integer price = 0;
        Integer countThreeForTwo = 0;
        Integer countTwoForOneAndHalf = 0;
        Integer currentMinForThreeForTwo = Integer.MAX_VALUE;
        Integer currentMinForTwoForOneAndHalf = Integer.MAX_VALUE;
        for (String productName : basket) {
            if(!products.containsKey(productName)) {
                throw new NotFoundException("There is not a product with such name in the db");
            }
            if(promotions.containsKey(productName)) {
                if(promotions.get(productName).equals("THREE_FOR_TWO")) {
                    countThreeForTwo++;
                    price += products.get(productName);
                    if(currentMinForThreeForTwo > products.get(productName)) {
                        currentMinForThreeForTwo = products.get(productName);
                    }
                    if(countThreeForTwo == 3 ) {
                        price -= currentMinForThreeForTwo;
                        currentMinForThreeForTwo = Integer.MAX_VALUE;
                        countThreeForTwo = 0;
                    }
                }
                else {
                    countTwoForOneAndHalf++;
                    price += products.get(productName);

                    if(currentMinForTwoForOneAndHalf > products.get(productName)) {
                        currentMinForTwoForOneAndHalf = products.get(productName);
                    }

                    if(countTwoForOneAndHalf == 2 ) {
                        price -= currentMinForTwoForOneAndHalf/2;
                        currentMinForTwoForOneAndHalf = Integer.MAX_VALUE;
                        countTwoForOneAndHalf = 0;
                    }
                }
            }
            else {
                price += products.get(productName);
            }
        }
     return String.format("%.2f aws", price / 100.0);
    }
}
