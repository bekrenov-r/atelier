package com.group.atelier.service;

import com.group.atelier.dto.mapper.HomepageImageMapper;
import com.group.atelier.dto.response.HomepageImageResponse;
import com.group.atelier.model.entity.HomepageImage;
import com.group.atelier.repository.HomepageImageRepository;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import static com.group.atelier.util.ResourcesConstants.*;

@Service
@RequiredArgsConstructor
public class HomepageImageService {
    private final HomepageImageRepository homepageImageRepository;
    private final HomepageImageMapper homepageImageMapper;

    public List<HomepageImageResponse> getAllImages() {
        return homepageImageRepository.findAll().stream()
                .map(homepageImageMapper::entityToResponse)
                .toList();
    }
}
