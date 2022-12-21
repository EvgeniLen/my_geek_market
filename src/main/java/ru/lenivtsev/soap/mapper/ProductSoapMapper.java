package ru.lenivtsev.soap.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import ru.lenivtsev.model.Product;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ProductSoapMapper {

    ru.lenivtsev.soap.Product map(Product product);

    //@Mapping(target = "id", ignore = true)
    Product map(ru.lenivtsev.soap.Product product);

}
