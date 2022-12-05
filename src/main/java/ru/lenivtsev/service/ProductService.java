package ru.lenivtsev.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.lenivtsev.model.dto.ProductDto;
import ru.lenivtsev.model.mapper.ProductDtoMapper;
import ru.lenivtsev.repository.ProductRepository;
import ru.lenivtsev.soap.Product;
import ru.lenivtsev.soap.mapper.ProductSoapMapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private static final Pattern PARAM_PATTERN_BETWEEN = Pattern.compile("(\\d+)?\\-(\\d+)?");
    private final ProductRepository productRepository;
    private final ProductDtoMapper mapper;
    private final ProductSoapMapper soapMapper;

    public Page<ProductDto> findAllByFilter(String productTitleFilter, String priceFilter, int page, int size, String sortField){
        productTitleFilter = productTitleFilter == null || productTitleFilter.isBlank() ? null : "%" + productTitleFilter.trim() + "%";
        priceFilter = priceFilter == null || priceFilter.isBlank() ? null : priceFilter.trim();
        BigDecimal priceFilterMin = null;
        BigDecimal priceFilterMax = null;
        if (priceFilter !=null){
            Matcher matcher = PARAM_PATTERN_BETWEEN.matcher(priceFilter);

            if (matcher.matches()) {
                priceFilterMin = matcher.group(1)==null ? null : new BigDecimal(matcher.group(1));
                priceFilterMax = matcher.group(2)==null ? null : new BigDecimal(matcher.group(2));
            }
        }
        //TODO: разобраться с сортировкой
        return productRepository.productByFilter(productTitleFilter, priceFilterMin, priceFilterMax, PageRequest.of(page, size, Sort.by(sortField)))
                .map(mapper::map);
    }

    public Optional<ProductDto> findByProductId(Long id) {
        return productRepository.findById(id).map(mapper::map);
    }

    public void save(ProductDto productDto) {
        productRepository.save(mapper.map(productDto));
    }

    public void deleteProductById(Long id){
        productRepository.deleteById(id);
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(soapMapper::map)
                .collect(Collectors.toList());
    }
}
