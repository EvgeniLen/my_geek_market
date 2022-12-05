package ru.lenivtsev.soap;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.lenivtsev.service.ProductService;

@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://www.geekbrains.ru/lenivtsev/ws/products";
    private final ProductService productService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProducts")
    @ResponsePayload
    public GetAllProductResponse getAllProducts(@RequestPayload GetAllProducts request){
        GetAllProductResponse response = new GetAllProductResponse();
        productService.findAllProducts().forEach(response.getProducts()::add);
        return response;
    }
}
