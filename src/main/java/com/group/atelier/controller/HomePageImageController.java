package com.group.atelier.controller;

import com.group.atelier.dto.response.HomePageImagesResponse;
import com.group.atelier.service.HomePageImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/homepage/images")
@RequiredArgsConstructor
public class HomePageImageController {
    private final HomePageImageService homePageImageService;

    @GetMapping
    public ResponseEntity<HomePageImagesResponse> getAllImages(){
        return ResponseEntity.ok(homePageImageService.getAllImages());
    }
}
