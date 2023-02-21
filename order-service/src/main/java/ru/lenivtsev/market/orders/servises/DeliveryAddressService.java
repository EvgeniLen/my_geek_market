package ru.lenivtsev.market.orders.servises;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lenivtsev.market.api.dto.DeliveryAddressDto;
import ru.lenivtsev.market.orders.model.mapper.DeliveryAddressMapper;
import ru.lenivtsev.market.orders.repositories.DeliveryAddressRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeliveryAddressService {
    private final DeliveryAddressRepository deliveryAddressRepository;
    private final DeliveryAddressMapper deliveryAddressMapper;

    public Optional<DeliveryAddressDto> getDeliveryAddress(String username){
       return Optional.ofNullable(deliveryAddressMapper.map(deliveryAddressRepository.getDeliveryAddressByUsername(username)));
    }
}
