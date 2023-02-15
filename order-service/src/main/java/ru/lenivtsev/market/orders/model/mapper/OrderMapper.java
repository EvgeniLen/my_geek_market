package ru.lenivtsev.market.orders.model.mapper;

import org.mapstruct.*;
import ru.lenivtsev.market.api.dto.OrderDto;
import ru.lenivtsev.market.orders.model.Order;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface OrderMapper {
    Order map(OrderDto orderDto);

    OrderDto map(Order order);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Order partialUpdate(OrderDto orderDto, @MappingTarget Order order);

    @AfterMapping
    default void linkOrderItems(@MappingTarget Order order) {
        order.getOrderItems().forEach(orderItem -> orderItem.setOrder(order));
    }
}