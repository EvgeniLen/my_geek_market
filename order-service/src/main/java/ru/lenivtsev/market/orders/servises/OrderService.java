package ru.lenivtsev.market.orders.servises;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import ru.lenivtsev.market.api.dto.BasketDto;
import ru.lenivtsev.market.api.dto.OrderDto;
import ru.lenivtsev.market.orders.integrations.BasketServiceIntegration;
import ru.lenivtsev.market.orders.listener.OrderEvent;
import ru.lenivtsev.market.orders.model.Order;
import ru.lenivtsev.market.orders.model.mapper.OrderMapper;
import ru.lenivtsev.market.orders.repositories.OrderRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final DeliveryAddressService deliveryAddressService;
    private final OrderItemService orderItemService;
    private final BasketServiceIntegration basketServiceIntegration;
    private final OrderMapper orderMapper;

    private final ApplicationEventPublisher applicationEventPublisher;

    @Transactional
    public void addBasketToOrder(Long basketId) {
        BasketDto basketDto = basketServiceIntegration.getBasket(basketId);
        String phoneNumber = "+823456565"; //TODO: прописать номер телефона в базе, и сделать возврат по userid
        LocalDateTime deliveryDate = LocalDateTime.now();
        OrderDto order = getOrderByOwnerId(basketDto.getUsername());

        if (order == null) {
            //order = new OrderDto(basketDto.getUsername(), 1L, basketDto.getTotalPrice(), deliveryAddressService.getDeliveryAddress(basketDto.getUsername()).get(), phoneNumber, deliveryDate);
            order = OrderDto.Builder.newBuilder()
                    .setUsername(basketDto.getUsername())
                    .setStatus(1L)
                    .setPrice(basketDto.getTotalPrice())
                    .setDeliveryAddress(deliveryAddressService.getDeliveryAddress(basketDto.getUsername()).get())
                    .setPhoneNumber(phoneNumber)
                    .setDeliveryDate(deliveryDate)
                    .build();
            orderItemService.takeItems(basketId, order);
            save(order);
        } else {
            order.setPrice(basketDto.getTotalPrice());
            orderItemService.deleteItems(order.getId());
            orderItemService.takeItems(basketId, order);
            save(order);
        }

    }

    public void save(OrderDto orderDto){
        Order order = orderMapper.map(orderDto);
        orderMapper.linkOrderItems(order);
        order.setDeliveryPrice(new BigDecimal(5));
        orderRepository.save(order);
        OrderEvent orderEvent = new OrderEvent(this, order);
        applicationEventPublisher.publishEvent(orderEvent);
    }
    public OrderDto getOrderByOwnerId(String username) {
        return orderMapper.map(orderRepository.findOrderByUsername(username));
    }


    public void checkout(Long id) {
        OrderDto order = orderMapper.map(orderRepository.getReferenceById(id));
        basketServiceIntegration.clearBasket(order.getUsername());
        order.setStatus(2L);
        save(order);

    }

}
