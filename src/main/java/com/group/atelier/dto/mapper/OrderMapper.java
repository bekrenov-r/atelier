package com.group.atelier.dto.mapper;

import com.group.atelier.dto.response.OrderResponse;
import com.group.atelier.model.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CoatModelMapper.class, PatternDataMapper.class})
public abstract class OrderMapper {
    public abstract OrderResponse entityToResponse(Order order);
}
