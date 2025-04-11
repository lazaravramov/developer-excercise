package com.devexercise.developer.Entity;

import com.devexercise.developer.Enum.PromotionTypeEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table (name = "promotions")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    @NotBlank(message = "Promotion name must not be blank")
    private String productName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PromotionTypeEnum promotionTypeEnum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public PromotionTypeEnum getPromotionTypeEnum() {
        return promotionTypeEnum;
    }

    public void setPromotionTypeEnum(PromotionTypeEnum promotionTypeEnum) {
        this.promotionTypeEnum = promotionTypeEnum;
    }
}
