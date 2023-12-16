package com.group.atelier.review;

import com.group.atelier.model.dto.request.ReviewRequest;
import com.group.atelier.model.dto.response.ReviewResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
@Validated
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/{coatModelId}")
    public ResponseEntity<Page<ReviewResponse>> getReviewsForCoatModel(
            @PathVariable Long coatModelId,
            @RequestParam(value = "page", defaultValue = "0") Integer page
    ) {
        return ResponseEntity.ok(reviewService.getReviewsForCoatModel(coatModelId, page));
    }

    @PostMapping
    @Secured("CLIENT")
    public ResponseEntity<Void> createReview(@RequestBody @Valid ReviewRequest request){
        reviewService.createReview(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PutMapping("/{id}")
    @Secured("CLIENT")
    public ResponseEntity<ReviewResponse> updateReview(
            @PathVariable Long id,
            @RequestParam(value = "content", required = false) String content,
            @RequestParam(value ="rating", required = false) @Min(1) @Max(5) Short rating){
        return ResponseEntity.ok(reviewService.updateReview(id, content, rating));
    }

    @DeleteMapping("/{id}")
    @Secured({"CLIENT", "ADMIN"})
    public ResponseEntity<Void> deleteReview(@PathVariable Long id){
        reviewService.deleteReview(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
