package com.group.atelier.order;

import com.group.atelier.model.entity.Client;
import com.group.atelier.model.entity.Employee;
import com.group.atelier.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByClient(Client client);

    List<Order> findAllByEmployee(Employee employee);

    @Query("from Order o where o.employee = null")
    List<Order> findAllUnassignedOrders();
}
