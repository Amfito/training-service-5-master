package com.accenture.ems.emstraining.business.repository;

import com.accenture.ems.emstraining.business.repository.model.TrainingTypeDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingTypeRepository extends JpaRepository<TrainingTypeDAO, Long> {
}
