package ru.lenivtsev.market.baskets.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import ru.lenivtsev.market.api.dto.BasketDto;
import ru.lenivtsev.market.baskets.model.Basket;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface BasketDtoMapper {

    BasketDto map(Basket basket);

    Basket map(BasketDto basketDto);
}
