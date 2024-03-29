package ru.lenivtsev.market.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BasketDto {

    private Long id;
    private String username;
    private BigDecimal totalPrice = BigDecimal.ZERO;


    public BasketDto(Long userId) {
        this.username = username;
    }
}
