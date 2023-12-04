package com.group.atelier.business.coatmodel;

import com.group.atelier.model.dto.response.CoatModelResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/coat-models")
@RequiredArgsConstructor
public class CoatModelController {
    private final CoatModelService coatModelService;

    @GetMapping
    public ResponseEntity<List<CoatModelResponse>> getAllCoatModels(){
        return ResponseEntity.ok(coatModelService.getAllCoatModels());
    }

    @GetMapping("/{id}/images")
    public ResponseEntity<Map<Long, byte[]>> getAllOrderImagesForCoatModel(@PathVariable Long id){
        return ResponseEntity.ok(coatModelService.getAllOrderImagesForCoatModel(id));
    }
}
