package com.group.atelier.controller;

import com.group.atelier.dto.response.HomepageImageResponse;
import com.group.atelier.service.HomepageImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/homepage/images")
@RequiredArgsConstructor
public class HomepageImageController {
    private final HomepageImageService homePageImageService;

    @GetMapping
    public ResponseEntity<List<HomepageImageResponse>> getAllImages(){
        return ResponseEntity.ok(homePageImageService.getAllImages());
    }
}
