package com.group.atelier.client.clientmetrics.dto;

import com.group.atelier.model.entity.ClientMetrics;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ClientMetricsMapper {
    public abstract ClientMetricsResponse entityToResponse(ClientMetrics clientMetrics);

    public abstract ClientMetrics requestToEntity(ClientMetricsRequest clientMetricsRequest);
}
