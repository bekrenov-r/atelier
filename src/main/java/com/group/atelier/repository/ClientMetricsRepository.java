package com.group.atelier.repository;

import com.group.atelier.model.entity.ClientMetrics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientMetricsRepository extends JpaRepository<ClientMetrics, Long> {
}
