package com.group.atelier.review;

import com.group.atelier.model.entity.CoatModel;
import com.group.atelier.model.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findAllByCoatModel(CoatModel coatModel, Pageable pageable);
}
