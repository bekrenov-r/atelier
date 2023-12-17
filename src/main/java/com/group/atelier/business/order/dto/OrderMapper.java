package com.group.atelier.business.order.dto;

import com.group.atelier.business.coatmodel.dto.CoatModelMapper;
import com.group.atelier.business.patterndata.dto.PatternDataMapper;
import com.group.atelier.business.productmetrics.dto.ProductMetricsMapper;
import com.group.atelier.model.entity.Order;
import com.group.atelier.util.ImageService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

@Mapper(componentModel = "spring", uses = {CoatModelMapper.class, PatternDataMapper.class, ProductMetricsMapper.class})
public abstract class OrderMapper {
    @Autowired
    protected ImageService imageService;

    @Mapping(target = "image", source = "imgPath", qualifiedByName = "mapOrderImage")
    public abstract OrderResponse entityToResponse(Order order);

    @Named("mapOrderImage")
    protected byte[] mapImage(String imgPath) throws IOException {
        return imageService.extractImage(imgPath);
    }
}
