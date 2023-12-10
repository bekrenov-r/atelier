package com.group.atelier.business.coatmodel;

import com.group.atelier.model.dto.request.CoatModelRequest;
import com.group.atelier.model.dto.response.CoatModelResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @PostMapping
    @Secured("EMPLOYEE")
    public ResponseEntity<CoatModelResponse> createCoatModel(@RequestBody CoatModelRequest request) throws IOException {
        return ResponseEntity.ok(coatModelService.createCoatModel(request));
    }

    @PatchMapping("/{id}/image")
    @Secured("EMPLOYEE")
    public ResponseEntity<Void> attachImageToCoatModel(
            @PathVariable Long id, @RequestParam("file") MultipartFile file
    ) throws IOException {
        coatModelService.attachImageToCoatModel(id, file);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
