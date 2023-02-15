package ru.lenivtsev.market.orders.model.mapper;

import org.mapstruct.*;
import ru.lenivtsev.market.api.dto.DeliveryAddressDto;
import ru.lenivtsev.market.orders.model.DeliveryAddress;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface DeliveryAddressMapper {
    DeliveryAddress map(DeliveryAddressDto deliveryAddressDto);

    DeliveryAddressDto map(DeliveryAddress deliveryAddress);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    DeliveryAddress updateDeliveryAddressFromDeliveryAddressDto(DeliveryAddressDto deliveryAddressDto, @MappingTarget DeliveryAddress deliveryAddress);
}
