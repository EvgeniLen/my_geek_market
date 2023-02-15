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

    @GetMapping
    public OrderDto getOrder(@RequestHeader(name = "username", required = false) String username){
        return orderService.getOrderByOwnerId(username);
    }

    @GetMapping("/proceedTo/{basketId}")
    public void proceedToCheckout(@PathVariable("basketId") Long basketId) {
        orderService.addBasketToOrder(basketId);
    }

    @DeleteMapping("/checkout/{id}")
    public void checkoutOrder(@PathVariable("id") Long id) {
        orderService.checkout(id);
    }

}
