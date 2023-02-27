package ru.lenivtsev.market.core.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import ru.lenivtsev.market.api.dto.ProductDto;
import ru.lenivtsev.market.core.model.Product;



@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ProductDtoMapper {

    ProductDto map(Product product);

    //@Mapping(target = "id", ignore = true)
    Product map(ProductDto productDto);

}
