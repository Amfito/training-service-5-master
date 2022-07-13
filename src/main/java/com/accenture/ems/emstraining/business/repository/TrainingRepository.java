package com.accenture.ems.emstraining.business.repository;

import com.accenture.ems.emstraining.business.repository.model.TrainingDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingRepository extends JpaRepository<TrainingDAO, Long> {
}
