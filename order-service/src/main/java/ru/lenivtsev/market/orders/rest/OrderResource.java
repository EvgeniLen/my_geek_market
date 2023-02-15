package ru.lenivtsev.market.orders.rest;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.lenivtsev.market.api.dto.OrderDto;
import ru.lenivtsev.market.orders.servises.OrderItemService;
import ru.lenivtsev.market.orders.servises.OrderService;

@RestController
@RequestMapping("api/v1/order")
@RequiredArgsConstructor
public class OrderResource {

    private final OrderService orderService;


    @GetMapping("/{userId}")
    public OrderDto listPage(@PathVariable("userId") Long userId){
        return orderService.getOrderByOwnerId(userId);
    }

    @GetMapping("/proceedTo/{basketId}")
    public String proceedToCheckout(@PathVariable("basketId") Long basketId) {
        orderService.addBasketToOrder(basketId);
        return "Оформление заказа";
    }

    @DeleteMapping("/checkout/{id}")
    public String checkoutOrder(@PathVariable("id") Long id) {
        orderService.checkout(id);
        return "Заказ оформлен!";
    }

}
