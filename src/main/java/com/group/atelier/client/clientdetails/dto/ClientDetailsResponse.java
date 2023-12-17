package com.group.atelier.client.clientdetails.dto;

import com.group.atelier.client.clientdetails.dto.address.AddressResponse;

import java.time.LocalDate;

public record ClientDetailsResponse(
        LocalDate birthDate,
        String phoneNumber,
        AddressResponse address
){ }
