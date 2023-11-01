package com.group.atelier.controller;

import com.group.atelier.dto.response.CoatModelResponse;
import com.group.atelier.service.CoatModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/coat-models")
@RequiredArgsConstructor
public class CoatModelController {
    private final CoatModelService coatModelService;

    @GetMapping
    public ResponseEntity<List<CoatModelResponse>> getAllImages(){
        return ResponseEntity.ok(coatModelService.getAllImages());
    }
}
