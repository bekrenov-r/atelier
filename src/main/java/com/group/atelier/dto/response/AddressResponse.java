package com.group.atelier.dto.response;

public record AddressResponse(
        Long id,
        String city,
        String street,
        String buildingNumber,
        String apartmentNumber,
        String zipCode
) { }
