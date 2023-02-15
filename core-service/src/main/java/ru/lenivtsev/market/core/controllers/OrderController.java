package ru.lenivtsev.market.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.lenivtsev.market.api.dto.OrderDto;
import ru.lenivtsev.market.api.dto.OrderItemDto;
import ru.lenivtsev.market.core.integrations.OrderServiceIntegration;

import java.util.List;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {



    private final OrderServiceIntegration orderServiceIntegration;

    @GetMapping
    public String orderPage(Model model, @RequestHeader String username){
        model.getAttribute("id");

        OrderDto order = orderServiceIntegration.getOrderByOwner(username);
        List<OrderItemDto> orderItems = order.getOrderItems();
        model.addAttribute("order", order);
        model.addAttribute("items", orderItems);
        return "order";
    }

    @GetMapping("/add/{id}")
    public String addBasketToOrder(@PathVariable("id") Long basketId){
        orderServiceIntegration.addBasketToOrder(basketId);
        return "redirect:/order";
    }

    @GetMapping("/checkout/{id}")
    public String checkoutOrder(@PathVariable("id") Long id) {
        orderServiceIntegration.checkout(id);
        return "redirect:/shop";
    }
}
