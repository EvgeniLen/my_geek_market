package ru.lenivtsev.market.auth.model.mapers;

import org.mapstruct.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.lenivtsev.market.api.dto.UserDto;
import ru.lenivtsev.market.auth.model.User;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface UserEncoderDtoMapper {

    //@Mapping(target = "id", ignore = true)
    @Mapping(source = "password", target = "password", qualifiedByName = "encode")
    User map(UserDto dto, @Context PasswordEncoder encoder);

    @Named("encode")
    default String encode(String password, @Context PasswordEncoder encoder) {
        return encoder.encode(password);
    }
}
