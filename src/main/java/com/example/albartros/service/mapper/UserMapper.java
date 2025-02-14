package com.example.albartros.service.mapper;

import com.example.albartros.dto.UserDto;
import com.example.albartros.model.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    @Named("toDto")
    @Mapping(target = "toursList", ignore = true)
    @Mapping(target = "authUserId", source = "authUser.id")
    public abstract UserDto toDto(User user);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "toursList", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    public abstract User toEntity(UserDto userDto);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "toursList", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract User updateUser(@MappingTarget User user, UserDto userDto);

    @IterableMapping(qualifiedByName = "toDto")
    public abstract List<UserDto> toDtoList(List<User> users);
}
