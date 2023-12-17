package com.group.atelier.business.productmetrics.dto;

import com.group.atelier.model.entity.ProductMetrics;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class ProductMetricsMapper {

    @Mapping(target = "neckSemiCircumference", source = "clientMetrics.neckSemiCircumference")
    @Mapping(target = "chestSemiCircumference1", source = "clientMetrics.chestSemiCircumference1")
    @Mapping(target = "chestSemiCircumference2", source = "clientMetrics.chestSemiCircumference2")
    @Mapping(target = "chestSemiCircumference3", source = "clientMetrics.chestSemiCircumference3")
    @Mapping(target = "waistSemiCircumference", source = "clientMetrics.waistSemiCircumference")
    @Mapping(target = "shoulderWidth", source = "clientMetrics.shoulderWidth")
    @Mapping(target = "chestHeight", source = "clientMetrics.chestHeight")
    @Mapping(target = "chestHeight1", source = "clientMetrics.chestHeight1")
    @Mapping(target = "backArmholeHeight", source = "clientMetrics.backArmholeHeight")
    @Mapping(target = "backLengthTillWaist", source = "clientMetrics.backLengthTillWaist")
    @Mapping(target = "shoulderHeightSidelong", source = "clientMetrics.shoulderHeightSidelong")
    @Mapping(target = "chestWidth", source = "clientMetrics.chestWidth")
    @Mapping(target = "chestCenter", source = "clientMetrics.chestCenter")
    @Mapping(target = "backWidth", source = "clientMetrics.backWidth")
    @Mapping(target = "waistLengthFront", source = "clientMetrics.waistLengthFront")
    @Mapping(target = "neckBaseToFrontWaistLineDistance", source = "clientMetrics.neckBaseToFrontWaistLineDistance")
    public abstract ProductMetrics dtoToEntity(ProductMetricsDTO dto);

    @Mapping(target = "clientMetrics.neckSemiCircumference", source = "neckSemiCircumference")
    @Mapping(target = "clientMetrics.chestSemiCircumference1", source = "chestSemiCircumference1")
    @Mapping(target = "clientMetrics.chestSemiCircumference2", source = "chestSemiCircumference2")
    @Mapping(target = "clientMetrics.chestSemiCircumference3", source = "chestSemiCircumference3")
    @Mapping(target = "clientMetrics.waistSemiCircumference", source = "waistSemiCircumference")
    @Mapping(target = "clientMetrics.shoulderWidth", source = "shoulderWidth")
    @Mapping(target = "clientMetrics.chestHeight", source = "chestHeight")
    @Mapping(target = "clientMetrics.chestHeight1", source = "chestHeight1")
    @Mapping(target = "clientMetrics.backArmholeHeight", source = "backArmholeHeight")
    @Mapping(target = "clientMetrics.backLengthTillWaist", source = "backLengthTillWaist")
    @Mapping(target = "clientMetrics.shoulderHeightSidelong", source = "shoulderHeightSidelong")
    @Mapping(target = "clientMetrics.chestWidth", source = "chestWidth")
    @Mapping(target = "clientMetrics.chestCenter", source = "chestCenter")
    @Mapping(target = "clientMetrics.backWidth", source = "backWidth")
    @Mapping(target = "clientMetrics.waistLengthFront", source = "waistLengthFront")
    @Mapping(target = "clientMetrics.neckBaseToFrontWaistLineDistance", source = "neckBaseToFrontWaistLineDistance")
    public abstract ProductMetricsDTO entityToDto(ProductMetrics productMetrics);
}
