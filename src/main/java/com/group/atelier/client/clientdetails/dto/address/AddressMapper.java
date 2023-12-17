package com.group.atelier.client.clientdetails.dto.address;

import com.group.atelier.model.entity.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class AddressMapper {
    public abstract AddressResponse entityToResponse(Address address);

    public abstract Address requestToEntity(AddressRequest addressRequest);
}
