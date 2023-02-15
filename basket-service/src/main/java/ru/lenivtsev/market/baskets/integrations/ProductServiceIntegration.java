package ru.lenivtsev.market.baskets.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.lenivtsev.market.api.dto.ProductDto;
import ru.lenivtsev.market.api.dto.exceptions.EntityNotFoundException;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {
    private final WebClient productServiceWebClient;

    public ProductDto getProductById(Long id) {
        return productServiceWebClient.get()
                .uri("/api/v1/product/"+ id)
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(),
                        clientResponse -> Mono.error(new EntityNotFoundException("Не удается найти продукт с id=" + id))
                )
                .bodyToMono(ProductDto.class)
                .block();
    }







    /*Старая часть на restTemplate, избавляемся потому нужна асинхронность
    private final RestTemplate restTemplate;

    public Optional<ProductDto> getProductById(Long id){

        return Optional.ofNullable(restTemplate.getForObject( "http://localhost:8081/api/v1/product/" + id, ProductDto.class));
    }*/

}
