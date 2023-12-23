package com.group.atelier.business.order.dto;

import com.group.atelier.business.coatmodel.dto.CoatModelMapper;
import com.group.atelier.business.patterndata.dto.PatternDataMapper;
import com.group.atelier.business.productmetrics.dto.ProductMetricsMapper;
import com.group.atelier.model.entity.Order;
import com.group.atelier.util.ImageService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {CoatModelMapper.class, PatternDataMapper.class, ProductMetricsMapper.class})
public abstract class OrderMapper {
    @Autowired
    protected ImageService imageService;

    public abstract OrderResponse entityToResponse(Order order);
}
