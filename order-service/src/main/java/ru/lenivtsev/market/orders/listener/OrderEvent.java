package ru.lenivtsev.market.orders.listener;

import org.springframework.context.ApplicationEvent;
import ru.lenivtsev.market.orders.model.Order;

public class OrderEvent extends ApplicationEvent {
    private final Order order;
    private final Long status;

    public OrderEvent(Object source, Order order) {
        super(source);
        this.order = order;
        this.status = order.getStatus();
    }

    public Order getOrder() {
        return order;
    }

    public Long getStatus() {
        return status;
    }
}
