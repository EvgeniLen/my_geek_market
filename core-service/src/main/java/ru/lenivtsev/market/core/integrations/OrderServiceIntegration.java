package ru.lenivtsev.market.core.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.lenivtsev.market.api.dto.BasketDto;
import ru.lenivtsev.market.api.dto.BasketItemDto;
import ru.lenivtsev.market.api.dto.OrderDto;
import ru.lenivtsev.market.api.dto.exceptions.EntityNotFoundException;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderServiceIntegration {
    private final RestTemplate restTemplate;
    private final WebClient orderServiceWebClient;

    public OrderDto getOrderByOwner(String username) {
        return orderServiceWebClient.get()
                .uri("/api/v1/order")
                .header("username", username)
                .retrieve()
                .bodyToMono(OrderDto.class)
                .block();
    }

    public void addBasketToOrder(Long basketId) {
        orderServiceWebClient.get()
                .uri("/api/v1/order/proceedTo/" + basketId)
                .retrieve()
                .toBodilessEntity()
                .block();
    }

    public void checkout(Long id) {
        orderServiceWebClient.get()
                .uri("/api/v1/order/checkout/" + id)
                .retrieve()
                .toBodilessEntity()
                .block();
    }


   /* public List<BasketItemDto> getItemsInOrder(long id) {

        return restTemplate.getForObject("http://localhost:8083/api/v1/order/getItems/" + id, List.class);
    }*/
}
