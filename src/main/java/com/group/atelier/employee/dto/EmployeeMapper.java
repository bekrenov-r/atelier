package com.group.atelier.employee.dto;

import com.group.atelier.model.entity.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class EmployeeMapper {
    public abstract Employee requestToEntity(EmployeeRegistrationRequest request);

    public abstract EmployeeResponse entityToResponse(Employee employee);
}
