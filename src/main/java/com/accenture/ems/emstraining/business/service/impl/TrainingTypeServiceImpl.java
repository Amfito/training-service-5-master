package com.accenture.ems.emstraining.business.service.impl;

import com.accenture.ems.emstraining.business.mappers.TrainingTypeMapStructMapper;
import com.accenture.ems.emstraining.business.repository.TrainingTypeRepository;
import com.accenture.ems.emstraining.business.repository.model.TrainingTypeDAO;
import com.accenture.ems.emstraining.business.service.TrainingTypeService;
import com.accenture.ems.emstraining.models.TrainingType;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
public class TrainingTypeServiceImpl implements TrainingTypeService {

    @Autowired
    TrainingTypeRepository trainingTypeRepository;

    @Autowired
    TrainingTypeMapStructMapper trainingTypeMapStructMapper;

    @Override
    public List<TrainingType> findAllTrainingType() {
        List<TrainingTypeDAO> trainingTypeDAOList = trainingTypeRepository.findAll();
        log.info("Get training type list. Size is: {}", trainingTypeDAOList::size);
        return trainingTypeDAOList.stream().map(trainingTypeMapStructMapper::trainingTypeDAOToTrainingType).collect(Collectors.toList());
    }

    @Override
    public Optional<TrainingType> findTrainingTypeById(Long id) {
        Optional<TrainingType> trainingTypeById = trainingTypeRepository.findById(id)
                .flatMap(trainingType -> Optional.ofNullable(trainingTypeMapStructMapper.trainingTypeDAOToTrainingType(trainingType)));
        log.info("Training type with id {} is {}", id, trainingTypeById);
        return trainingTypeById;
    }

    @Override
    public TrainingType saveTrainingType(TrainingType trainingType) throws Exception {
        TrainingTypeDAO trainingTypeSaved = trainingTypeRepository.save(trainingTypeMapStructMapper.trainingTypeToTrainingTypeDAO(trainingType));
        log.info("New Training type saved: {}", () -> trainingTypeSaved);
        return trainingTypeMapStructMapper.trainingTypeDAOToTrainingType(trainingTypeSaved);
    }

    @Override
    public void deleteTrainingTypeById(Long id) {
        trainingTypeRepository.deleteById(id);
        log.info("Training Type with id {} is deleted", id);
    }
}
