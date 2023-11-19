package com.group.atelier.model.dto.request;

public record AddressRequest(
        String city,
        String street,
        String buildingNumber,
        String apartmentNumber,
        String zipCode
) { }
