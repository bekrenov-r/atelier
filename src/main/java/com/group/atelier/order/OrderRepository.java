package com.group.atelier.order;

import com.group.atelier.model.entity.Client;
import com.group.atelier.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByClient(Client client);

    @Query("from Order o where o.employee = null")
    List<Order> findAllUnassignedOrders();
}
