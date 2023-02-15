package ru.lenivtsev.market.auth.model;

import org.mapstruct.*;
import ru.lenivtsev.market.api.dto.RoleDto;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface RoleMapper {
    Role map(RoleDto roleDto);

    RoleDto map(Role role);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Role partialUpdate(RoleDto roleDto, @MappingTarget Role role);
}