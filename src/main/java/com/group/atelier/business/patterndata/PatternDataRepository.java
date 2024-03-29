package com.group.atelier.business.patterndata;

import com.group.atelier.model.entity.PatternData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatternDataRepository extends JpaRepository<PatternData, Long> {
}
