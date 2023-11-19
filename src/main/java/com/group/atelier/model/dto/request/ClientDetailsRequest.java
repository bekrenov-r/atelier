package com.group.atelier.model.dto.request;

import java.time.LocalDate;

public record ClientDetailsRequest(
        LocalDate birthDate,
        String phoneNumber,
        AddressRequest address
) { }
