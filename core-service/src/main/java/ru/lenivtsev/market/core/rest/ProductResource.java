package ru.lenivtsev.market.core.rest;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.lenivtsev.market.api.dto.ProductDto;
import ru.lenivtsev.market.api.dto.exceptions.EntityNotFoundException;
import ru.lenivtsev.market.core.service.ProductService;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
public class ProductResource {

    private final ProductService service;

    @GetMapping
    public Page<ProductDto> listPage(
            @RequestParam(required = false) String productTitleFilter,
            @RequestParam(required = false) String productCostFilter,
            @RequestParam(required = false) Optional<Integer> page,
            @RequestParam(required = false) Optional<Integer> size,
            @RequestParam(required = false) Optional<String> sortField) {
        int pageValue = page.orElse(1) - 1;
        if (pageValue < 0) pageValue = 0;
        int sizeValue = size.orElse((10));
        String sortFiledValue = sortField.filter(s -> !s.isBlank()).orElse("id");
        Page<ProductDto> allByFilter = service.findAllByFilter(productTitleFilter, productCostFilter, pageValue, sizeValue, sortFiledValue);
        //List<ProductDto> products = allByFilter.get().collect(Collectors.toList());
        return allByFilter;
    }

    @GetMapping("/{id}")
    public ProductDto form(@PathVariable("id") long id) {
        return service.findByProductId(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
    }

    @PostMapping
    public ProductDto saveProduct(@RequestBody ProductDto product) {
        if (product.getId() != null) {
           throw new IllegalArgumentException("Created product shouldn't have id");
        }
        service.save(product);
        return product;
    }

    @DeleteMapping("{id}")
    public void deleteProductById(@PathVariable long id) {
        service.deleteProductById(id);
    }

    @PutMapping()
    public ProductDto updateProduct(@RequestBody ProductDto product) {
        service.save(product);
        return product;
    }
}
