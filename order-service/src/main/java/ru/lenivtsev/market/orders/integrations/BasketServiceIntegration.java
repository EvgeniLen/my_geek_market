package ru.lenivtsev.market.orders.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.lenivtsev.market.api.dto.BasketDto;
import ru.lenivtsev.market.api.dto.BasketItemDto;
import ru.lenivtsev.market.api.dto.wrappers.BasketItemDtoList;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BasketServiceIntegration {
    private final RestTemplate restTemplate;

    public BasketDto getBasket(long id){
        return restTemplate.getForObject("http://localhost:8082/api/v1/basket/get/" + id, BasketDto.class);
    }
    public BasketDto getBasketForOwner(long userId){
        return restTemplate.getForObject("http://localhost:8082/api/v1/basket/" + userId, BasketDto.class);
    }

    public List<BasketItemDto> getItemsInBasket(long id) {
        BasketItemDtoList response = restTemplate.getForObject("http://localhost:8082/api/v1/basket/getItems/" + id, BasketItemDtoList.class);
        return response.getList();
    }

    public String addProductInBasket(Long userId, long id) {
       return restTemplate.getForObject("http://localhost:8082/api/v1/basket/add/" + userId + "/" + id, String.class);
    }

    public void deleteItemInBasket(long id) {
        restTemplate.delete("http://localhost:8082/api/v1/basket/delete/" + id);
    }

    public void clearBasket(long id){
        restTemplate.delete("http://localhost:8082/api/v1/basket/clear/" + id);
    }
}
