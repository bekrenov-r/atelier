package com.group.atelier.client;

import com.group.atelier.model.entity.Client;
import com.group.atelier.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByUser(User user);

    boolean existsByEmail(String email);
}
