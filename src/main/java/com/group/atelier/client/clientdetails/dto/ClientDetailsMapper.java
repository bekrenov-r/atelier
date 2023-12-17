package com.group.atelier.client.clientdetails.dto;

import com.group.atelier.client.clientdetails.dto.address.AddressMapper;
import com.group.atelier.model.entity.ClientDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = AddressMapper.class)
public abstract class ClientDetailsMapper {
    public abstract ClientDetailsResponse entityToResponse(ClientDetails clientDetails);

    public abstract ClientDetails requestToEntity(ClientDetailsRequest clientDetailsRequest);
}
