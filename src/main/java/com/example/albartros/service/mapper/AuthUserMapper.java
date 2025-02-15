package com.example.albartros.service.mapper;

import com.example.albartros.dto.AuthUserDto;
import com.example.albartros.model.AuthUser;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class AuthUserMapper {

    @Named("toDto")
    @Mapping(target = "password", ignore = true)
//    @Mapping(target = "userId", ignore = true)
    public abstract AuthUserDto toDto(AuthUser authUser);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    public abstract AuthUser toEntity(AuthUserDto authUserDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract AuthUser updateAuthUser(@MappingTarget AuthUser authUser, AuthUserDto authUserDto);

    @IterableMapping(qualifiedByName = "toDto")
    public abstract List<AuthUserDto> toDtoList(List<AuthUser> authUsers);
}
