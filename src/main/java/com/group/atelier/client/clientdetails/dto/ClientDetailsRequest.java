package com.group.atelier.client.clientdetails.dto;

import com.group.atelier.client.clientdetails.dto.address.AddressRequest;

import java.time.LocalDate;

public record ClientDetailsRequest(
        LocalDate birthDate,
        String phoneNumber,
        AddressRequest address
) { }
