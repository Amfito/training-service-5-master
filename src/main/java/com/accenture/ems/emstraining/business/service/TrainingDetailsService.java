package com.accenture.ems.emstraining.business.service;

import com.accenture.ems.emstraining.models.TrainingDetails;
import java.util.List;
import java.util.Optional;

public interface TrainingDetailsService {

    List<TrainingDetails> findAllTrainingDetails();

    Optional<TrainingDetails> findTrainingDetailsById(Long id);

    TrainingDetails saveTrainingDetails(TrainingDetails trainingDetails) throws Exception;

    void deleteTrainingDetailsById(Long id);
}
