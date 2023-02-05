package ru.lenivtsev.market.baskets.rest;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.lenivtsev.market.api.dto.BasketDto;
import ru.lenivtsev.market.api.dto.BasketItemDto;
import ru.lenivtsev.market.api.dto.wrappers.BasketItemDtoList;
import ru.lenivtsev.market.baskets.services.BasketItemService;
import ru.lenivtsev.market.baskets.services.BasketService;

import java.util.List;

@RestController
@RequestMapping("api/v1/basket")
@RequiredArgsConstructor
public class BasketResource {

    private final BasketService basketService;
    private final BasketItemService basketItemService;

    @GetMapping("/{userId}")
    public BasketDto listPage(@PathVariable("userId") Long userId){
        return basketService.findBasketByOwner(userId);
    }

    /*@GetMapping("/{userId}")
    public BasketDto form(@PathVariable("userId") long userId) {
        return basketService.findBasketByOwner(userId)
                .orElseThrow(() -> new EntityNotFoundException("B not found"));
    }*/

    @GetMapping("/add/{userId}/{id}")
    public String addProductToCart(@PathVariable("userId") Long userId, @PathVariable("id") Long id) {
        basketService.addToBasket(userId, id);
        return "Продукт добавлен в корзину";
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProductById(@PathVariable long id) {
        basketService.deleteItemInBasket(id);
    }

    @GetMapping("/get/{id}")
    public BasketDto getBasket(@PathVariable("id") Long id) {
        return basketService.findBasketById(id).get();
    }



    @DeleteMapping("/clear/{id}")
    public void clearBasket(@PathVariable("id") long id) {
        basketService.clearBasket(id);
    }

    @GetMapping("/getItems/{id}")
    public BasketItemDtoList getItemsInBasket(@PathVariable("id") long id) {
        BasketItemDtoList basketItemDtoList = new BasketItemDtoList();
        basketItemDtoList.setList(basketItemService.getBasketItems(id));
        return basketItemDtoList;
    }

    @GetMapping("/getItems1/{id}")
    public List<BasketItemDto> getItemsInBasket1(@PathVariable("id") long id) {
        return basketItemService.getBasketItems(id);
    }

}
