package ru.lenivtsev.market.api.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryAddressDto {
    private Long id;
    private String username;
    private String address;
}