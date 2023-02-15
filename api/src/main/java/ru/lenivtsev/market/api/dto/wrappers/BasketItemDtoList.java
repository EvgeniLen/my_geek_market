package ru.lenivtsev.market.api.dto.wrappers;


import ru.lenivtsev.market.api.dto.BasketItemDto;

import java.util.ArrayList;
import java.util.List;

public class BasketItemDtoList {
    private List<BasketItemDto> list;

    public BasketItemDtoList() {
        list = new ArrayList<>();
    }

    public BasketItemDtoList(List<BasketItemDto> list) {
        this.list = list;
    }

    public List<BasketItemDto> getList() {
        return list;
    }

    public void setList(List<BasketItemDto> list) {
        this.list = list;
    }
}
