package com.group.atelier.business.coatmodel;

import com.group.atelier.model.dto.mapper.CoatModelMapper;
import com.group.atelier.model.dto.response.CoatModelResponse;
import com.group.atelier.business.coatmodel.CoatModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoatModelService {
    private final CoatModelRepository coatModelRepository;
    private final CoatModelMapper coatModelMapper;

    public List<CoatModelResponse> getAllImages() {
        return coatModelRepository.findAll().stream()
                .map(coatModelMapper::entityToResponse)
                .toList();
    }
}
