package ru.lenivtsev.market.orders.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lenivtsev.market.orders.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findOrderByUsername(String name);
}
