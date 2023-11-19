package com.group.atelier.model.dto.mapper;

import com.group.atelier.model.dto.request.ClientDetailsRequest;
import com.group.atelier.model.dto.response.ClientDetailsResponse;
import com.group.atelier.model.entity.ClientDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = AddressMapper.class)
public abstract class ClientDetailsMapper {
    public abstract ClientDetailsResponse entityToResponse(ClientDetails clientDetails);

    public abstract ClientDetails requestToEntity(ClientDetailsRequest clientDetailsRequest);
}
