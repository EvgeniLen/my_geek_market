package ru.lenivtsev.market.orders.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.lenivtsev.market.api.dto.BasketDto;
import ru.lenivtsev.market.api.dto.BasketItemDto;
import ru.lenivtsev.market.api.dto.exceptions.EntityNotFoundException;
import ru.lenivtsev.market.api.dto.wrappers.BasketItemDtoList;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BasketServiceIntegration {


    private final WebClient basketServiceWebClient;

    public BasketDto getBasket(long id) {
        return basketServiceWebClient.get()
                .uri("/api/v1/basket/get/" + id)
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(),
                        clientResponse -> Mono.error(new EntityNotFoundException("Не удается найти корзину"))
                )
                .bodyToMono(BasketDto.class)
                .block();
    }

    public List<BasketItemDto> getItemsInBasket(long id) {
        return basketServiceWebClient.get()
                .uri("/api/v1/basket/getItems/" + id)
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(),
                        clientResponse -> Mono.error(new EntityNotFoundException("Не удается найти корзину"))
                )
                .bodyToMono(BasketItemDtoList.class)
                .block().getList();
    }

    public void clearBasket(String username) {
        basketServiceWebClient.get()
                .uri("/api/v1/basket/clear")
                .header("username", username)
                .retrieve()
                .toBodilessEntity()
                .block();
    }


    //private final RestTemplate restTemplate;
//    public BasketDto getBasketForOwner(long userId){
//        return restTemplate.getForObject("http://localhost:8082/api/v1/basket/" + userId, BasketDto.class);
//    }
//
//    public String addProductInBasket(Long userId, long id) {
//        return restTemplate.getForObject("http://localhost:8082/api/v1/basket/add/" + userId + "/" + id, String.class);
//    }
//
//    public void deleteItemInBasket(long id) {
//        restTemplate.delete("http://localhost:8082/api/v1/basket/delete/" + id);
//    }
}
