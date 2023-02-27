package ru.lenivtsev.market.auth.model.mapers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import ru.lenivtsev.market.api.dto.UserDto;
import ru.lenivtsev.market.auth.model.User;


@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface UserDtoMapper {

    @Mapping(target = "password", ignore = true)
    UserDto map(User user);

    User map(UserDto userDto);
}
