package ru.lenivtsev.model.dto;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link ru.lenivtsev.model.BasketItem} entity
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BasketItemDto implements Serializable {
    private Long id;
    private Long quantity;
    private BigDecimal itemPrice;
    private BigDecimal totalPrice;
    private ProductDto product;
    private BasketDto basket;

    public BasketItemDto(Long quantity, BigDecimal itemPrice, BigDecimal totalPrice, ProductDto product, BasketDto basket) {
        this.quantity = quantity;
        this.itemPrice = itemPrice;
        this.totalPrice = totalPrice;
        this.product = product;
        this.basket = basket;
    }
}