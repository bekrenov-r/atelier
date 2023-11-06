package com.group.atelier.dto.request;

import jakarta.persistence.Column;

public record AddressRequest(
        String city,
        String street,
        String buildingNumber,
        String apartmentNumber,
        String zipCode
) { }
