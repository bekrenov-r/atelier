package com.group.atelier.employee;

import com.group.atelier.client.ClientRepository;
import com.group.atelier.exception.ApplicationException;
import com.group.atelier.model.dto.mapper.EmployeeMapper;
import com.group.atelier.model.dto.request.EmployeeRegistrationRequest;
import com.group.atelier.model.dto.response.EmployeeResponse;
import com.group.atelier.model.entity.Employee;
import com.group.atelier.model.entity.User;
import com.group.atelier.model.enums.OrderStatus;
import com.group.atelier.security.Role;
import com.group.atelier.security.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static com.group.atelier.exception.ApplicationExceptionReason.*;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final UserService userService;
    private final ClientRepository clientRepository;

    public List<EmployeeResponse> getAllEmployees(){
        return employeeRepository.findAll().stream()
                .filter(e -> !e.getUser().hasRole(Role.ADMIN))
                .map(employeeMapper::entityToResponse)
                .toList();
    }

    public EmployeeResponse registerEmployee(EmployeeRegistrationRequest request) {
        assertEmailIsUnique(request.email());
        Employee employee = employeeMapper.requestToEntity(request);
        User user = userService.createUser(request, Set.of(Role.EMPLOYEE));
        employee.setUser(user);
        employee.setRegisteredAt(LocalDateTime.now());
        return employeeMapper.entityToResponse(employeeRepository.save(employee));
    }

    public void dismissEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(EMPLOYEE_NOT_FOUND, id));
        assertEmployeeHasNoOrdersInProgress(employee);
        employee.getUser().setActive(false);
        employeeRepository.save(employee);
    }

    private void assertEmailIsUnique(String email){
        if(employeeRepository.existsByEmail(email) || clientRepository.existsByEmail(email))
            throw new ApplicationException(EMAIL_ALREADY_EXISTS, email);
    }

    private void assertEmployeeHasNoOrdersInProgress(Employee employee){
        boolean hasOrdersInProgress = employee.getOrders()
                .stream()
                .anyMatch(o -> o.getStatus().equals(OrderStatus.IN_PROGRESS));
        if(hasOrdersInProgress)
            throw new ApplicationException(EMPLOYEE_HAS_UNFINISHED_ORDERS, employee.getId());
    }
}
