package com.group.atelier.dto.mapper;

import com.group.atelier.dto.request.ClientDetailsRequest;
import com.group.atelier.dto.request.ClientMetricsRequest;
import com.group.atelier.dto.response.ClientDetailsResponse;
import com.group.atelier.dto.response.ClientMetricsResponse;
import com.group.atelier.model.entity.ClientDetails;
import com.group.atelier.model.entity.ClientMetrics;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = AddressMapper.class)
public abstract class ClientDetailsMapper {
    public abstract ClientDetailsResponse entityToResponse(ClientDetails clientDetails);

    public abstract ClientDetails requestToEntity(ClientDetailsRequest clientDetailsRequest);
}
