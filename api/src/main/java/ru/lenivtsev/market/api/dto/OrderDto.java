package ru.lenivtsev.market.api.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private  Long id;
    private  Long userId;
    private  List<OrderItemDto> orderItems;
    private  Long status;
    private  BigDecimal price;
    private  BigDecimal deliveryPrice;
    private  DeliveryAddressDto deliveryAddress;
    private  String phoneNumber;
    private  LocalDateTime deliveryDate;

    public OrderDto(Long userId, Long status, BigDecimal price, DeliveryAddressDto deliveryAddress, String phoneNumber, LocalDateTime deliveryDate) {
        this.userId = userId;
        this.status = status;
        this.price = price;
        this.deliveryAddress = deliveryAddress;
        this.phoneNumber = phoneNumber;
        this.deliveryDate = deliveryDate;
    }
}