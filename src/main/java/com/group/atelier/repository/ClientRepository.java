package com.group.atelier.repository;

import com.group.atelier.model.Client;
import com.group.atelier.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
