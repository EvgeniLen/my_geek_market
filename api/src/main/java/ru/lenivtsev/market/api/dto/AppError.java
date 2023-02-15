package ru.lenivtsev.market.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class AppError {
    private int statusCode;
    private String message;


}
