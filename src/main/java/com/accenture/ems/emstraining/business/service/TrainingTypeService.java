package com.accenture.ems.emstraining.business.service;



import com.accenture.ems.emstraining.models.TrainingType;

import java.util.List;
import java.util.Optional;

public interface TrainingTypeService {

    List<TrainingType> findAllTrainingType();

    Optional<TrainingType> findTrainingTypeById(Long id);

    TrainingType saveTrainingType(TrainingType trainingType) throws Exception;
}
