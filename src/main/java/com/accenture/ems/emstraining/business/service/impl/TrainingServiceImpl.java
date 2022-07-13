package com.accenture.ems.emstraining.business.service.impl;

import com.accenture.ems.emstraining.business.mappers.TrainingMapStructMapper;
import com.accenture.ems.emstraining.business.repository.TrainingRepository;
import com.accenture.ems.emstraining.business.repository.model.TrainingDAO;
import com.accenture.ems.emstraining.business.service.TrainingService;
import com.accenture.ems.emstraining.models.Training;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
public class TrainingServiceImpl implements TrainingService {

    @Autowired
    TrainingRepository trainingRepository;

    @Autowired
    TrainingMapStructMapper trainingMapStructMapper;

    @Override
    public List<Training> findAllTraining() {
        List<TrainingDAO> trainingDAOList = trainingRepository.findAll();
        log.info("Get training list. Size is: {}", trainingDAOList::size);
        return trainingDAOList.stream().map(trainingMapStructMapper::trainingDAOToTraining).collect(Collectors.toList());
    }

    @Override
    public Optional<Training> findTrainingById(Long id) {
        Optional<Training> trainingById = trainingRepository.findById(id)
                .flatMap(training -> Optional.ofNullable(trainingMapStructMapper.trainingDAOToTraining(training)));
        log.info("Training with id {} is {}", id, trainingById);
        return trainingById;
    }

    @Override
    public Training saveTraining(Training training) throws Exception {
        TrainingDAO trainingSaved = trainingRepository.save(trainingMapStructMapper.trainingToTrainingDAO(training));
        log.info("New Training saved: {}", () -> trainingSaved);
        return trainingMapStructMapper.trainingDAOToTraining(trainingSaved);
    }

    @Override
    public void deleteTrainingById(Long id) {
        trainingRepository.deleteById(id);
        log.info("Training with id {} is deleted", id);
    }
}
