package com.group.atelier.client.clientdetails.dto.address;

public record AddressRequest(
        String city,
        String street,
        String buildingNumber,
        String apartmentNumber,
        String zipCode
) { }
