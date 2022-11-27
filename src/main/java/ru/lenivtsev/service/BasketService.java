package ru.lenivtsev.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lenivtsev.model.dto.BasketDto;
import ru.lenivtsev.model.dto.BasketItemDto;
import ru.lenivtsev.model.dto.ProductDto;
import ru.lenivtsev.model.dto.UserDto;
import ru.lenivtsev.model.mapper.BasketDtoMapper;
import ru.lenivtsev.model.mapper.ProductDtoMapper;
import ru.lenivtsev.model.mapper.UserDtoMapper;
import ru.lenivtsev.repository.BasketRepository;
import ru.lenivtsev.security.UserDetailsServiceImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketService {

    private final BasketRepository basketRepository;
    private final ProductService productService;
    private final BasketDtoMapper basketDtoMapper;
    private final UserDtoMapper userDtoMapper;
    private final BasketItemService basketItemService;
    private final UserDetailsServiceImpl userDetailsService;


    public void save(BasketDto basketDto) {
        basketRepository.save(basketDtoMapper.map(basketDto));
    }

    public Optional<BasketDto> findBasketById(long id) {
        return basketRepository.findById(id).map(basketDtoMapper::map);
    }

    public Optional<BasketDto> findBasketByOwner(UserDto user) {
        long id = userDtoMapper.map(user).getId();
        //return basketDtoMapper.map(basketRepository.findBasketByOwnerId(id));
        return basketRepository.findBasketByOwnerId(id).map(basketDtoMapper::map);
    }

    public void addToBasket(Long id) {
        UserDto user = userDetailsService.getAuthentication().get();
        BasketDto basketDto = findBasketByOwner(user).orElse(new BasketDto());
        ProductDto productDto =  productService.findByProductId(id).get();
        if (basketDto.getUser() == null) {
            basketDto.setUser(user);
            save(basketDto);
            basketDto = findBasketByOwner(user).get();
        }
        basketItemService.addProductInItem(productDto, basketDto);
        recalculate(basketDto);
    }

    public void recalculate(BasketDto basketDto){
        List<BasketItemDto> basketDtoList = basketItemService.getBasketItems(basketDto.getId());
        BigDecimal totalPrice = BigDecimal.ZERO;
        totalPrice = basketDtoList.stream()
                .map(BasketItemDto::getTotalPrice)
                .reduce(totalPrice, BigDecimal::add);
        basketDto.setTotalPrice(totalPrice);
        save(basketDto);
    }

    public void deleteBasket(Long basketId){
        basketRepository.deleteById(basketId);
    }


    public void deleteItemInBasket(long itemId) {
        BasketDto basketDto = basketItemService.getItem(itemId).getBasket();
        basketItemService.deleteItem(itemId);
        if (basketItemService.getBasketItems(basketDto.getId()).isEmpty()){
            deleteBasket(basketDto.getId());
        } else {
            recalculate(basketDto);
        }
    }
}
