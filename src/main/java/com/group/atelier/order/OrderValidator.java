package com.group.atelier.order;

import com.group.atelier.client.ClientRepository;
import com.group.atelier.employee.EmployeeRepository;
import com.group.atelier.exception.ApplicationException;
import com.group.atelier.model.entity.Client;
import com.group.atelier.model.entity.Employee;
import com.group.atelier.model.entity.Order;
import com.group.atelier.model.entity.User;
import com.group.atelier.model.enums.OrderStatus;
import com.group.atelier.security.Role;
import com.group.atelier.util.CurrentUserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.stereotype.Component;

import static com.group.atelier.exception.ApplicationExceptionReason.NOT_ENTITY_OWNER;
import static com.group.atelier.exception.ApplicationExceptionReason.ORDER_ALREADY_ASSIGNED;
import static com.group.atelier.security.Role.CLIENT;
import static com.group.atelier.security.Role.EMPLOYEE;

@Component
@RequiredArgsConstructor
public class OrderValidator {
    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;
    private final CurrentUserUtil currentUserUtil;

    public void validateOrderOwnershipByClient(Order order){
        Client currentClient = clientRepository.findByUser(currentUserUtil.getCurrentUser());
        if(!order.getClient().equals(currentClient))
            throw new ApplicationException(NOT_ENTITY_OWNER);
    }

    public void validateOrderOwnershipByEmployee(Order order){
        Employee currentEmployee = employeeRepository.findByUser(currentUserUtil.getCurrentUser());
        if(!order.getEmployee().equals(currentEmployee))
            throw new ApplicationException(NOT_ENTITY_OWNER);
    }

    public void validateBeforeAssignment(Order order){
        if(order.getEmployee() != null)
            throw new ApplicationException(ORDER_ALREADY_ASSIGNED, order.getId());
    }

    public void validateStatusDependingOnRole(OrderStatus status){
        if(status.equals(OrderStatus.DONE)){
            requireRole(EMPLOYEE);
        } else if(status.equals(OrderStatus.CANCELLED)){
            requireRole(CLIENT);
        }
    }

    private void requireRole(Role role){
        User currentUser = currentUserUtil.getCurrentUser();
        if(!currentUser.hasRole(role))
            throw new AuthorizationServiceException("You don't have authority required for this action");
    }
}
