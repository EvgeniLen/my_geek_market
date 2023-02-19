package ru.lenivtsev.market.baskets.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.lenivtsev.market.api.dto.BasketDto;
import ru.lenivtsev.market.api.dto.BasketItemDto;
import ru.lenivtsev.market.api.dto.ProductDto;
import ru.lenivtsev.market.baskets.integrations.ProductServiceIntegration;
import ru.lenivtsev.market.baskets.model.mapper.BasketDtoMapper;
import ru.lenivtsev.market.baskets.repository.BasketRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketService {

    private final BasketRepository basketRepository;
    private final BasketDtoMapper basketDtoMapper;
    private final BasketItemService basketItemService;

    private final RedisTemplate<String, BasketDto> redisTemplate;

    private final ProductServiceIntegration productServiceIntegration;

    public void save(BasketDto basketDto) {
        basketRepository.save(basketDtoMapper.map(basketDto));
    }

    public Optional<BasketDto> findBasketById(long id) {
        return basketRepository.findById(id).map(basketDtoMapper::map);
    }

    public BasketDto findBasketByOwner(String username) {
        //UserDto user = userServiceIntegration.getUser();
        //long id = user.getId();
        BasketDto basketDto;
        if (Boolean.FALSE.equals(redisTemplate.hasKey(username))){
            basketDto = basketRepository.findBasketByUsername(username).map(basketDtoMapper::map).orElse(new BasketDto());
            if (basketDto.getUsername() == null) {
                basketDto.setUsername(username);
                save(basketDto);
                basketDto = basketDtoMapper.map(basketRepository.findBasketByUsername(username).get());
            }
            redisTemplate.opsForValue().set(username, basketDto);
        }

        return redisTemplate.opsForValue().get(username);
    }

   // @Transactional
    public void addToBasket(String username, long id) {
        BasketDto basketDto = findBasketByOwner(username);
        //BasketDto basketDto = basketDtoMapper.map(basketRepository.findBasketByOwnerId(userId).get());
        ProductDto productDto =  productServiceIntegration.getProductById(id);
        /*if (basketDto.getUser() == null) {
            basketDto.setUser(user);
            save(basketDto);
            basketDto = findBasketByOwner(user).get();
        }*/
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

    public void deleteItemInBasket(long itemId) {
        BasketDto basketDto = basketItemService.getItem(itemId).getBasket();
        basketItemService.deleteItem(itemId);
        if (!basketItemService.getBasketItems(basketDto.getId()).isEmpty()){
            recalculate(basketDto);
            //deleteBasket(basketDto.getId());
        }
    }

    public void clearBasket(String username) {
        //basketRepository.deleteById(id);
        basketRepository.deleteById(basketRepository.findBasketByUsername(username).get().getId());
        redisTemplate.delete(username);
    }
}
