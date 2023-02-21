package ru.lenivtsev.market.orders.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lenivtsev.market.orders.model.DeliveryAddress;

@Repository
public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress, Long> {
    DeliveryAddress getDeliveryAddressByUsername(String name);
}
