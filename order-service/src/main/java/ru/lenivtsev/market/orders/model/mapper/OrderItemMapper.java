package ru.lenivtsev.market.orders.model.mapper;

import org.mapstruct.*;
import ru.lenivtsev.market.api.dto.OrderItemDto;
import ru.lenivtsev.market.orders.model.OrderItem;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface OrderItemMapper {
    OrderItem map(OrderItemDto orderItemDto);

    OrderItemDto map(OrderItem orderItem);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    OrderItem updateOrderItemFromOrderItemDto(OrderItemDto orderItemDto, @MappingTarget OrderItem orderItem);


}
