package com.group.atelier.model.dto.response;

public record AddressResponse(
        Long id,
        String city,
        String street,
        String buildingNumber,
        String apartmentNumber,
        String zipCode
) { }
