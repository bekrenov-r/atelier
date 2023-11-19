package com.group.atelier.model.dto.mapper;

import com.group.atelier.model.dto.response.OrderResponse;
import com.group.atelier.model.entity.Order;
import com.group.atelier.business.patterndata.dto.PatternDataMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CoatModelMapper.class, PatternDataMapper.class, ProductMetricsMapper.class})
public abstract class OrderMapper {
    public abstract OrderResponse entityToResponse(Order order);
}
