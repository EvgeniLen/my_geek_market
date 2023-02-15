package ru.lenivtsev.market.baskets.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
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

    @GetMapping
    public String listPage(Model model, @RequestHeader String username) {
        BasketDto basketDto = basketService.findBasketByOwner(username);
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
    @GetMapping("/clear")
    public String clearBasket(@PathVariable("username") String username) {
        basketService.clearBasket(username);
        return "redirect:/shop";
    }


    // TODO: увеличивать\уменьшать кол-во товаров в корзине
}
