package com.accenture.ems.emstraining.business.mappers;

import com.accenture.ems.emstraining.business.repository.model.TrainingDAO;
import com.accenture.ems.emstraining.models.Training;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {TrainingTypeMapStructMapper.class})
public interface TrainingMapStructMapper {

    TrainingDAO trainingToTrainingDAO(Training training);

    Training trainingDAOToTraining(TrainingDAO trainingDAO);
}
