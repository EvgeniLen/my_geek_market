package ru.lenivtsev.market.orders.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.lenivtsev.market.orders.model.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    public void deleteAllByOrderId(Long id);
}
