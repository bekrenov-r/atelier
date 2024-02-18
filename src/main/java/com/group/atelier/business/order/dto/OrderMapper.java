package com.group.atelier.business.order.dto;

import com.group.atelier.business.coatmodel.dto.CoatModelMapper;
import com.group.atelier.business.patterndata.dto.PatternDataMapper;
import com.group.atelier.business.productmetrics.dto.ProductMetricsMapper;
import com.group.atelier.model.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CoatModelMapper.class, PatternDataMapper.class, ProductMetricsMapper.class})
public abstract class OrderMapper {
    public abstract OrderResponse entityToResponse(Order order);

    @Mapping(target = "coatModelName", source = "coatModel.name")
    @Mapping(target = "price", source = "coatModel.price")
    @Mapping(target = "coatType", source = "coatModel.coatType")
    public abstract OrderShortResponse entityToShortResponse(Order order);
}
