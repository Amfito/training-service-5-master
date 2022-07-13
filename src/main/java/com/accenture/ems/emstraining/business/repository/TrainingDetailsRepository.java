package com.accenture.ems.emstraining.business.repository;

import com.accenture.ems.emstraining.business.repository.model.TrainingDetailsDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingDetailsRepository extends JpaRepository<TrainingDetailsDAO, Long> {
}
