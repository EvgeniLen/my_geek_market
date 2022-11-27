package ru.lenivtsev.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BasketDto {

    private Long id;
    private UserDto user;
    private BigDecimal totalPrice = BigDecimal.ZERO;


    public BasketDto(UserDto user) {
        this.user = user;
    }
}
