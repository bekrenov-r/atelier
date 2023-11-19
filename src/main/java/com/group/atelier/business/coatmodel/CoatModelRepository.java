package com.group.atelier.business.coatmodel;

import com.group.atelier.model.entity.CoatModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoatModelRepository extends JpaRepository<CoatModel, Long> {
}
