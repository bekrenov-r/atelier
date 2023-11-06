package com.group.atelier.dto.mapper;

import com.group.atelier.dto.request.ClientMetricsRequest;
import com.group.atelier.dto.response.ClientMetricsResponse;
import com.group.atelier.model.entity.ClientMetrics;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ClientMetricsMapper {
    public abstract ClientMetricsResponse entityToResponse(ClientMetrics clientMetrics);

    public abstract ClientMetrics requestToEntity(ClientMetricsRequest clientMetricsRequest);
}
