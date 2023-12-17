package com.group.atelier.client.dto;

import com.group.atelier.model.entity.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ClientMapper {
    public abstract ClientResponse entityToResponse(Client client);
}
