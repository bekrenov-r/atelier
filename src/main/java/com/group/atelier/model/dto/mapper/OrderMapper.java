package com.group.atelier.model.dto.mapper;

import com.group.atelier.business.patterndata.dto.PatternDataMapper;
import com.group.atelier.model.dto.response.OrderResponse;
import com.group.atelier.model.entity.Order;
import com.group.atelier.order.OrderImageService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

@Mapper(componentModel = "spring", uses = {CoatModelMapper.class, PatternDataMapper.class, ProductMetricsMapper.class})
public abstract class OrderMapper {
    @Autowired
    protected OrderImageService orderImageService;

    @Mapping(target = "image", source = "imgPath", qualifiedByName = "mapOrderImage")
    public abstract OrderResponse entityToResponse(Order order);

    @Named("mapOrderImage")
    protected byte[] mapImage(String imgPath) throws IOException {
        return orderImageService.extractImage(imgPath);
    }
}
