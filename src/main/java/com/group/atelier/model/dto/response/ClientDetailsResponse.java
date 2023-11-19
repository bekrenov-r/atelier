package com.group.atelier.model.dto.response;

import java.time.LocalDate;

public record ClientDetailsResponse(
        LocalDate birthDate,
        String phoneNumber,
        AddressResponse address
){ }
