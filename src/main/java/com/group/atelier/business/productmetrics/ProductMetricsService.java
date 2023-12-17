package com.group.atelier.business.productmetrics;

import com.group.atelier.business.productmetrics.dto.ProductMetricsDTO;
import com.group.atelier.business.productmetrics.dto.ProductMetricsMapper;
import com.group.atelier.model.entity.ProductMetrics;
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
