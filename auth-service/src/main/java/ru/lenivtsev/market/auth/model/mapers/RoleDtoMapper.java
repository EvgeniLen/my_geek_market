package ru.lenivtsev.market.auth.model.mapers;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import ru.lenivtsev.market.api.dto.RoleDto;
import ru.lenivtsev.market.auth.model.Role;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface RoleDtoMapper {

    RoleDto map(Role role);

    Role map(RoleDto roleDto);
}
