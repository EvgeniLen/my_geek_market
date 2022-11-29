package ru.lenivtsev.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.lenivtsev.model.dto.BasketDto;
import ru.lenivtsev.model.dto.BasketItemDto;
import ru.lenivtsev.model.dto.UserDto;
import ru.lenivtsev.security.UserDetailsServiceImpl;
import ru.lenivtsev.service.BasketItemService;
import ru.lenivtsev.service.BasketService;
import ru.lenivtsev.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller

@RequestMapping("/shop")
@RequiredArgsConstructor
public class ShopController {
    private final UserDetailsServiceImpl userDetailsService;
    private final BasketService basketService;
    private final BasketItemService basketItemService;
    private final ProductService productService;

    @GetMapping
    public String listPage(
            @RequestParam(required = false) String productTitleFilter,
            @RequestParam(required = false) String productPriceFilter,
            @RequestParam(required = false) Optional<Integer> page,
            @RequestParam(required = false) Optional<Integer> size,
            @RequestParam(required = false) Optional<String> sortField,
            Model model) {
        int pageValue = page.orElse(1) - 1;
        if (pageValue < 0) pageValue = 0;
        int sizeValue = size.orElse((10));
        String sortFiledValue = sortField.filter(s -> !s.isBlank()).orElse("id");

        UserDto user = userDetailsService.getAuthentication().get();
        BasketDto basketDto = basketService.findBasketByOwner(user).orElse(new BasketDto(user));
        List<BasketItemDto> basketItemsDto = basketItemService.getBasketItems(basketDto.getId());
        model.addAttribute("basket", basketDto);
        model.addAttribute("items", basketItemsDto);
        model.addAttribute("products", productService.findAllByFilter(productTitleFilter, productPriceFilter, pageValue, sizeValue, sortFiledValue));
        return "shop";
    }

    @GetMapping("/add/{id}")
    public String addProductToCart(@PathVariable("id") Long id, HttpServletRequest httpServletRequest) {
        basketService.addToBasket(id);
        return "redirect:/shop";
    }

    // TODO: добавить переадчу с фронда id корзины, что бы не искать каждый раз в базе
    @GetMapping("/delete/{id}")
    public String deleteProductById(@PathVariable long id) {
        basketService.deleteItemInBasket(id);
        return "redirect:/shop";
    }
    @GetMapping("/clear/{id}")
    public String clearBasket(@PathVariable("id") long id) {
        basketService.clearBasket(id);
        return "redirect:/shop";
    }


    // TODO: увеличивать\уменьшать кол-во товаров в корзине
}
