package ru.lenivtsev.market.orders.identityMaps;

import ru.lenivtsev.market.orders.model.Order;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;

public class UnitOfOrder {
    private Map<String, Order> orderMap = Collections.synchronizedMap(new IdentityHashMap<>());

    public void addOrder(Order order){
        orderMap.put(order.getUsername(), order);
    }

    public Order getOrder(String username){
        return orderMap.get(username);
    }


}
