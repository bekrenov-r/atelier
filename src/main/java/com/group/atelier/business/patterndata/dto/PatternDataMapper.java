package com.group.atelier.business.patterndata.dto;

import com.group.atelier.model.dto.response.PatternDataResponse;
import com.group.atelier.model.entity.PatternData;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class PatternDataMapper {
    public abstract PatternDataResponse entityToResponse(PatternData patternData);
}
