package com.group.atelier.model.dto.mapper;

import com.group.atelier.model.dto.request.AddressRequest;
import com.group.atelier.model.dto.response.AddressResponse;
import com.group.atelier.model.entity.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class AddressMapper {
    public abstract AddressResponse entityToResponse(Address address);

    public abstract Address requestToEntity(AddressRequest addressRequest);
}
