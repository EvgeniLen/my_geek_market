package ru.lenivtsev.market.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.lenivtsev.market.api.dto.OrderDto;
import ru.lenivtsev.market.api.dto.OrderItemDto;
import ru.lenivtsev.market.api.dto.UserDto;
import ru.lenivtsev.market.core.integrations.OrderServiceIntegration;
import ru.lenivtsev.market.core.security.UserDetailsServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final UserDetailsServiceImpl userDetailsService;

    private final OrderServiceIntegration orderServiceIntegration;

    @GetMapping
    public String orderPage(Model model){
        model.getAttribute("id");
        UserDto user = userDetailsService.getAuthentication().get();
        OrderDto order = orderServiceIntegration.getOrderByOwnerId(user.getId());
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
