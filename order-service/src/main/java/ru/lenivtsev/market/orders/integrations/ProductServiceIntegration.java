package ru.lenivtsev.market.orders.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.lenivtsev.market.api.dto.ProductDto;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {
    private final RestTemplate restTemplate;

    public Optional<ProductDto> getProductById(Long id){
        return Optional.ofNullable(restTemplate.getForObject("http://localhost:8081/api/v1/product/" + id, ProductDto.class));
    }

}
