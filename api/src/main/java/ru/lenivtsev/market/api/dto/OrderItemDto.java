package ru.lenivtsev.market.api.dto;

import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto{
    private  Long id;
    private  Long quantity;
    private  BigDecimal itemPrice;
    private  BigDecimal totalPrice;
    private  Long productId;
    private  String title;

    public OrderItemDto(Long quantity, BigDecimal itemPrice, BigDecimal totalPrice, Long productId, String title) {
        this.quantity = quantity;
        this.itemPrice = itemPrice;
        this.totalPrice = totalPrice;
        this.productId = productId;
        this.title = title;
    }
}