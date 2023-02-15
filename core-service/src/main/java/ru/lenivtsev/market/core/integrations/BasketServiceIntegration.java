package ru.lenivtsev.market.core.integrations;

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
    private final RestTemplate restTemplate;

    private final WebClient basketServiceWebClient;

    public BasketDto getBasketForOwner(String username) {
        return basketServiceWebClient.get()
                .uri("/api/v1/basket/")
                .header("username", username)
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


    public String addProductInBasket(long id) {
        return basketServiceWebClient.get()
                .uri("/api/v1/basket/add/" + id)
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(),
                        clientResponse -> Mono.error(new EntityNotFoundException("Не удается найти корзину"))
                )
                .bodyToMono(String.class)
                .block();
    }

    public void deleteItemInBasket(long id) {
        basketServiceWebClient.get()
                .uri("/api/v1/basket/delete/" + id)
                .retrieve()
                .toBodilessEntity()
                .block();
    }

    public void clearBasket(String username) {
        basketServiceWebClient.get()
                .uri("/api/v1/basket/clear")
                .header("username", username)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
