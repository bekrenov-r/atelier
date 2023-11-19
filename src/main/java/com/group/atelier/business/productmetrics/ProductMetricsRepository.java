package com.group.atelier.business.productmetrics;

import com.group.atelier.model.entity.ProductMetrics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMetricsRepository extends JpaRepository<ProductMetrics, Long> {
}
