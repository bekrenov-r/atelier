package com.group.atelier.client.clientdetails.dto.address;

public record AddressResponse(
        Long id,
        String city,
        String street,
        String buildingNumber,
        String apartmentNumber,
        String zipCode
) { }
