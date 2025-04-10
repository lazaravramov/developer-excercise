package com.devexercise.developer.Entity;

import com.devexercise.developer.Enum.PromotionTypeEnum;
import jakarta.persistence.*;

@Entity
@Table (name = "promotions")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String productName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PromotionTypeEnum promotionTypeEnum;

}
