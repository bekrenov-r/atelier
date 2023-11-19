package com.group.atelier.employee;

import com.group.atelier.model.entity.Employee;
import com.group.atelier.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByUser(User user);
}
