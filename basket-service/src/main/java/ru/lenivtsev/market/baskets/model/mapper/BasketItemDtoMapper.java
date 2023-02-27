package ru.lenivtsev.market.baskets.model.mapper;

import org.mapstruct.*;
import ru.lenivtsev.market.api.dto.BasketItemDto;
import ru.lenivtsev.market.baskets.model.BasketItem;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface BasketItemDtoMapper {
    BasketItem map(BasketItemDto basketItemDto);

    BasketItemDto map(BasketItem basketItem);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BasketItem updateBasketItemFromBasketItemDto(BasketItemDto basketItemDto, @MappingTarget BasketItem basketItem);
}
