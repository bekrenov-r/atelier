package com.group.atelier.order;

import com.group.atelier.client.ClientRepository;
import com.group.atelier.employee.EmployeeRepository;
import com.group.atelier.exception.ApplicationException;
import com.group.atelier.model.entity.Client;
import com.group.atelier.model.entity.Employee;
import com.group.atelier.model.entity.Order;
import com.group.atelier.model.enums.OrderStatus;
import com.group.atelier.util.CurrentUserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.group.atelier.exception.ApplicationExceptionReason.*;

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

    public void assertOrderIsNotCancelled(Order order){
        if(order.getStatus().equals(OrderStatus.CANCELLED))
            throw new ApplicationException(ORDER_ALREADY_CANCELLED, order.getId());
    }

    public void assertOrderIsNotCompleted(Order order){
        if(order.getStatus().equals(OrderStatus.COMPLETED))
            throw new ApplicationException(ORDER_ALREADY_COMPLETED, order.getId());
    }

    public void assertOrderIsInProgress(Order order){
        if(!order.getStatus().equals(OrderStatus.IN_PROGRESS))
            throw new ApplicationException(ORDER_NOT_ASSIGNED, order.getId());
    }
}
