package com.example.albartros.service.mapper;

import com.example.albartros.dto.MemoDto;
import com.example.albartros.model.Memo;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class MemoMapper {

    @Mapping(target = "country", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    public abstract Memo toEntity(MemoDto dto);

    @Named("toDto")
    @Mapping(target = "countryId", ignore = true)
    public abstract MemoDto toDto(Memo memo);

    @Mapping(target = "country", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Memo updatedAllFields(@MappingTarget Memo memo, MemoDto dto);

    @IterableMapping(qualifiedByName = "toDto")
    public abstract List<MemoDto> toDtoList(List<Memo> memos);
}
