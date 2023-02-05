package ru.lenivtsev.market.api.dto;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BasketItemDto implements Serializable {
    private Long id;
    private Long quantity;
    private BigDecimal itemPrice;
    private BigDecimal totalPrice;
    private Long productId;
    private BasketDto basket;
    private String title;

    public BasketItemDto(Long quantity, BigDecimal itemPrice, BigDecimal totalPrice, Long productId, BasketDto basket, String title) {
        this.quantity = quantity;
        this.itemPrice = itemPrice;
        this.totalPrice = totalPrice;
        this.productId = productId;
        this.basket = basket;
        this.title = title;
    }
}