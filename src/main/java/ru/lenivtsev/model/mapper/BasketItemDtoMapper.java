package ru.lenivtsev.model.mapper;

import org.mapstruct.*;
import ru.lenivtsev.model.BasketItem;
import ru.lenivtsev.model.dto.BasketItemDto;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface BasketItemDtoMapper {
    BasketItem map(BasketItemDto basketItemDto);

    BasketItemDto map(BasketItem basketItem);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BasketItem updateBasketItemFromBasketItemDto(BasketItemDto basketItemDto, @MappingTarget BasketItem basketItem);
}
