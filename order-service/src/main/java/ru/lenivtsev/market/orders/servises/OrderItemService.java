package ru.lenivtsev.market.orders.servises;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lenivtsev.market.api.dto.BasketItemDto;
import ru.lenivtsev.market.api.dto.OrderDto;
import ru.lenivtsev.market.api.dto.OrderItemDto;
import ru.lenivtsev.market.orders.integrations.BasketServiceIntegration;
import ru.lenivtsev.market.orders.integrations.ProductServiceIntegration;
import ru.lenivtsev.market.orders.model.mapper.OrderItemMapper;
import ru.lenivtsev.market.orders.repositories.OrderItemRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final BasketServiceIntegration basketServiceIntegration;
    private final OrderItemMapper orderItemMapper;

    public void save(List<OrderItemDto> orderItems){
        orderItemRepository.saveAll(orderItems.stream().map(orderItemMapper::map).toList());
    }


    public void takeItems(Long id, OrderDto order) {
        List<BasketItemDto> basketItem = basketServiceIntegration.getItemsInBasket(id);
        List<OrderItemDto> orderItems = basketItem
                .stream().collect(
                        ArrayList::new,
                        (list, bi)-> list.add(new OrderItemDto(bi.getQuantity(), bi.getItemPrice(), bi.getTotalPrice(),
                                bi.getProductId(), bi.getTitle())),
                        ArrayList::addAll);

        order.setOrderItems(orderItems);
        //orderService.save(order, orderItems);
        //save(orderItems);
    }

    public void deleteItems(Long id) {
        orderItemRepository.deleteAllByOrderId(id);
    }


}
