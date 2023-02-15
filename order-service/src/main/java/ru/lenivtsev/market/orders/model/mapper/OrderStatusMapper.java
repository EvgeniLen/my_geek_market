package ru.lenivtsev.market.orders.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.lenivtsev.market.api.dto.OrderStatusDto;
import ru.lenivtsev.market.orders.model.OrderStatus;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface OrderStatusMapper {
    OrderStatus map(OrderStatusDto OrderStatusDto);

    OrderStatusDto map(OrderStatus OrderStatus);

}