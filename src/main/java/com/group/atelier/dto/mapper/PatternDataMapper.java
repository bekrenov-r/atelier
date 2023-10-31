package com.group.atelier.dto.mapper;

import com.group.atelier.controller.PatternDataResponse;
import com.group.atelier.model.entity.PatternData;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class PatternDataMapper {
    public abstract PatternDataResponse entityToResponse(PatternData entity);
}
