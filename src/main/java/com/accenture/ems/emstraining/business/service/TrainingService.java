package com.accenture.ems.emstraining.business.service;



import com.accenture.ems.emstraining.models.Training;

import java.util.List;
import java.util.Optional;

public interface TrainingService {

    List<Training> findAllTraining();

    Optional<Training> findTrainingById(Long id);

    Training saveTraining(Training training) throws Exception;
}
