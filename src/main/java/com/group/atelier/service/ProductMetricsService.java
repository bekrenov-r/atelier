package com.group.atelier.service;

import com.group.atelier.dto.ProductMetricsDTO;
import com.group.atelier.dto.mapper.ProductMetricsMapper;
import com.group.atelier.model.entity.ProductMetrics;
import com.group.atelier.repository.ProductMetricsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductMetricsService {
    private final ProductMetricsRepository productMetricsRepository;
    private final ProductMetricsMapper productMetricsMapper;

    public ProductMetrics save(ProductMetricsDTO dto){
        ProductMetrics productMetrics = productMetricsMapper.dtoToEntity(dto);
        return productMetricsRepository.save(productMetrics);
    }
}
