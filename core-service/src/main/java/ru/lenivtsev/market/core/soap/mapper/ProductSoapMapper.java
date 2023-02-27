package ru.lenivtsev.market.core.soap.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import ru.lenivtsev.market.core.soap.Product;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ProductSoapMapper {

    Product map(ru.lenivtsev.market.core.model.Product product);

    //@Mapping(target = "id", ignore = true)
    ru.lenivtsev.market.core.model.Product map(Product product);

}
