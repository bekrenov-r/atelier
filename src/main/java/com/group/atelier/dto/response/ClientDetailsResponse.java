package com.group.atelier.dto.response;

import com.group.atelier.dto.request.AddressRequest;

import java.time.LocalDate;

public record ClientDetailsResponse(
        LocalDate birthDate,
        String phoneNumber,
        AddressResponse address
){ }
