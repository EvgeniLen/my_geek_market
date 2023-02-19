package ru.lenivtsev.market.baskets.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lenivtsev.market.api.dto.BasketDto;
import ru.lenivtsev.market.api.dto.BasketItemDto;
import ru.lenivtsev.market.api.dto.ProductDto;
import ru.lenivtsev.market.baskets.model.mapper.BasketItemDtoMapper;
import ru.lenivtsev.market.baskets.repository.BasketItemRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BasketItemService {
    private final BasketItemRepository basketItemRepository;
    private final BasketItemDtoMapper basketItemDtoMapper;

    public List<BasketItemDto> getBasketItems(Long basketId){
        return basketItemRepository.findBasketItemsByBasketId(basketId)
                .stream()
                .map(basketItemDtoMapper::map)
                .collect(Collectors.toList());
    }
    public Optional<BasketItemDto> getBasketItem(Long productId, Long basketId){
        return basketItemRepository.findBasketItemByProductIdAndBasketId(productId, basketId).map(basketItemDtoMapper::map);
    }

    public BasketItemDto getItem(Long itemId){
        return basketItemDtoMapper.map(basketItemRepository.findById(itemId).get());
    }

    public void save(BasketItemDto basketItemDto){
        basketItemRepository.save(basketItemDtoMapper.map(basketItemDto));
    }

    public void addProductInItem(ProductDto productDto, BasketDto basketDto){
        BasketItemDto basketItemDto = getBasketItem(productDto.getId(), basketDto.getId())
                .orElse(new BasketItemDto(0L, BigDecimal.ZERO, BigDecimal.ZERO, productDto.getId(), basketDto, productDto.getTitle()));
            Long count = basketItemDto.getQuantity();
            count++;
            basketItemDto.setItemPrice(productDto.getPrice());
            basketItemDto.setQuantity(count);
            basketItemDto.setTotalPrice(basketItemDto.getTotalPrice().add(basketItemDto.getItemPrice()));
            save(basketItemDto);
    }

    public void deleteItem(Long itemId){
        basketItemRepository.deleteById(itemId);
    }

}
