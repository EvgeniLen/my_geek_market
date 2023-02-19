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
    private  String username;
    private  List<OrderItemDto> orderItems;
    private  Long status;
    private  BigDecimal price;
    private  BigDecimal deliveryPrice;
    private  DeliveryAddressDto deliveryAddress;
    private  String phoneNumber;
    private  LocalDateTime deliveryDate;

    private OrderDto(Builder builder) {
        setUsername(builder.username);
        setOrderItems(builder.orderItems);
        setStatus(builder.status);
        setPrice(builder.price);
        setDeliveryPrice(builder.deliveryPrice);
        setDeliveryAddress(builder.deliveryAddress);
        setPhoneNumber(builder.phoneNumber);
        setDeliveryDate(builder.deliveryDate);
    }

    public static Builder newBuilder(OrderDto copy) {
        Builder builder = new Builder();
        builder.username = copy.getUsername();
        builder.orderItems = copy.getOrderItems();
        builder.status = copy.getStatus();
        builder.price = copy.getPrice();
        builder.deliveryPrice = copy.getDeliveryPrice();
        builder.deliveryAddress = copy.getDeliveryAddress();
        builder.phoneNumber = copy.getPhoneNumber();
        builder.deliveryDate = copy.getDeliveryDate();
        return builder;
    }

    public static final class Builder {
        private String username;
        private List<OrderItemDto> orderItems;
        private Long status;
        private BigDecimal price;
        private BigDecimal deliveryPrice;
        private DeliveryAddressDto deliveryAddress;
        private String phoneNumber;
        private LocalDateTime deliveryDate;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder setUsername(String val) {
            username = val;
            return this;
        }

        public Builder setOrderItems(List<OrderItemDto> val) {
            orderItems = val;
            return this;
        }

        public Builder setStatus(Long val) {
            status = val;
            return this;
        }

        public Builder setPrice(BigDecimal val) {
            price = val;
            return this;
        }

        public Builder setDeliveryPrice(BigDecimal val) {
            deliveryPrice = val;
            return this;
        }

        public Builder setDeliveryAddress(DeliveryAddressDto val) {
            deliveryAddress = val;
            return this;
        }

        public Builder setPhoneNumber(String val) {
            phoneNumber = val;
            return this;
        }

        public Builder setDeliveryDate(LocalDateTime val) {
            deliveryDate = val;
            return this;
        }

        public OrderDto build() {
            return new OrderDto(this);
        }
    }
}