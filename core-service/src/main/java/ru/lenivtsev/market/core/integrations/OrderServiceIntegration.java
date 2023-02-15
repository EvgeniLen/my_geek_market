package ru.lenivtsev.market.core.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.lenivtsev.market.api.dto.BasketItemDto;
import ru.lenivtsev.market.api.dto.OrderDto;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderServiceIntegration {
    private final RestTemplate restTemplate;

    public OrderDto getOrderByOwnerId(Long id) {
        return restTemplate.getForObject("http://localhost:8083/api/v1/order/" + id, OrderDto.class);
    }

    public String addBasketToOrder(Long basketId) {
        return restTemplate.getForObject("http://localhost:8083/api/v1/order/proceedTo/" + basketId, String.class);
    }

    public void checkout(Long id) {
        restTemplate.delete("http://localhost:8083/api/v1/order/checkout/" + id);
    }

   /* public List<BasketItemDto> getItemsInOrder(long id) {

        return restTemplate.getForObject("http://localhost:8083/api/v1/order/getItems/" + id, List.class);
    }*/
}
