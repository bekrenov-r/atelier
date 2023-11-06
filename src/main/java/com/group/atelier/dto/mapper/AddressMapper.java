package com.group.atelier.dto.mapper;

import com.group.atelier.dto.request.AddressRequest;
import com.group.atelier.dto.request.ClientMetricsRequest;
import com.group.atelier.dto.response.AddressResponse;
import com.group.atelier.dto.response.ClientMetricsResponse;
import com.group.atelier.model.entity.Address;
import com.group.atelier.model.entity.ClientMetrics;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class AddressMapper {
    public abstract AddressResponse entityToResponse(Address address);

    public abstract Address requestToEntity(AddressRequest addressRequest);
}
