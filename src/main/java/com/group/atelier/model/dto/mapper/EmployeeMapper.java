package com.group.atelier.model.dto.mapper;

import com.group.atelier.model.dto.request.EmployeeRegistrationRequest;
import com.group.atelier.model.dto.response.EmployeeResponse;
import com.group.atelier.model.entity.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class EmployeeMapper {
    public abstract Employee requestToEntity(EmployeeRegistrationRequest request);

    public abstract EmployeeResponse entityToResponse(Employee employee);
}
