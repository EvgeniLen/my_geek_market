package ru.lenivtsev.market.baskets.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.lenivtsev.market.api.dto.BasketDto;
import ru.lenivtsev.market.api.dto.BasketItemDto;
import ru.lenivtsev.market.baskets.services.BasketItemService;
import ru.lenivtsev.market.baskets.services.BasketService;

import java.util.List;


@Slf4j
@Controller
@RequestMapping("/basket")
@RequiredArgsConstructor
public class BasketController {

    private final BasketService basketService;
    private final BasketItemService basketItemService;

    @GetMapping("/{userId}")
    public String listPage(Model model, @PathVariable("userId") Long userId) {
        BasketDto basketDto = basketService.findBasketByOwner(userId);
        List<BasketItemDto> basketItemsDto = basketItemService.getBasketItems(basketDto.getId());
        model.addAttribute("basket", basketDto);
        model.addAttribute("items", basketItemsDto);
        return "basket";
    }

    /*@GetMapping("/add/{id}")
    public String addProductToCart(@PathVariable("id") Long id, HttpServletRequest httpServletRequest) {
        basketService.addToBasket(id);
        return "redirect:/shop";
    }*/

    // TODO: добавить переадчу с фронда id корзины, что бы не искать каждый раз в базе
    @GetMapping("/delete/{id}")
    public String deleteProductById(@PathVariable long id) {
        basketService.deleteItemInBasket(id);
        return "redirect:/basket";
    }
    @GetMapping("/clear/{id}")
    public String clearBasket(@PathVariable("id") long id) {
        basketService.clearBasket(id);
        return "redirect:/shop";
    }


    // TODO: увеличивать\уменьшать кол-во товаров в корзине
}
