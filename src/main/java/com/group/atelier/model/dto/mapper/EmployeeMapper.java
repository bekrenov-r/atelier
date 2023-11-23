package com.group.atelier.model.dto.mapper;

import com.group.atelier.model.dto.request.EmployeeRegistrationRequest;
import com.group.atelier.model.dto.response.EmployeeResponse;
import com.group.atelier.model.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class EmployeeMapper {
    public abstract Employee requestToEntity(EmployeeRegistrationRequest request);

    @Mapping(target = "isActive", source = "user.active")
    public abstract EmployeeResponse entityToResponse(Employee employee);
}
