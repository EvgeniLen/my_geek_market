package ru.lenivtsev.market.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.lenivtsev.market.api.dto.BasketDto;
import ru.lenivtsev.market.api.dto.BasketItemDto;
import ru.lenivtsev.market.core.integrations.BasketServiceIntegration;
import ru.lenivtsev.market.core.service.ProductService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ShopController {

    private final ProductService productService;
    private final BasketServiceIntegration basketServiceIntegration;

    @GetMapping
    public String listPage(
            @RequestParam(required = false) String productTitleFilter,
            @RequestParam(required = false) String productPriceFilter,
            @RequestParam(required = false) Optional<Integer> page,
            @RequestParam(required = false) Optional<Integer> size,
            @RequestParam(required = false) Optional<String> sortField,
            Model model,
            @RequestHeader String username) {
        int pageValue = page.orElse(1) - 1;
        if (pageValue < 0) pageValue = 0;
        int sizeValue = size.orElse((10));
        String sortFiledValue = sortField.filter(s -> !s.isBlank()).orElse("id");

        //UserDto user = userDetailsService.getAuthentication().get();
        BasketDto basketDto = basketServiceIntegration.getBasketForOwner(username);
        List<BasketItemDto> basketItemsDto = basketServiceIntegration.getItemsInBasket(basketDto.getId());
        model.addAttribute("basket", basketDto);
        model.addAttribute("items", basketItemsDto);
        model.addAttribute("products", productService.findAllByFilter(productTitleFilter, productPriceFilter, pageValue, sizeValue, sortFiledValue));
        return "shop";
    }

    @GetMapping("/add/{id}")
    public String addProductToCart(@PathVariable("id") Long id) {
        basketServiceIntegration.addProductInBasket(id);
        //basketService.addToBasket(id);
        return "redirect:/shop";
    }

    @GetMapping("/delete/{id}")
    public String deleteProductById(@PathVariable long id) {
        basketServiceIntegration.deleteItemInBasket(id);
        return "redirect:/shop";
    }
    @GetMapping("/clear")
    public String clearBasket(@RequestHeader String username) {
        //UserDto user = userDetailsService.getAuthentication().get();
        basketServiceIntegration.clearBasket(username);
        return "redirect:/shop";
    }


    // TODO: увеличивать\уменьшать кол-во товаров в корзине
}
