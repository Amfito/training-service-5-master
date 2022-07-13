package com.accenture.ems.emstraining.business.mappers;

import com.accenture.ems.emstraining.business.repository.model.TrainingDetailsDAO;
import com.accenture.ems.emstraining.models.TrainingDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {TrainingMapStructMapper.class})
public interface TrainingDetailsMapStructMapper {

    TrainingDetailsDAO trainingDetailsToTrainingDetailsDAO(TrainingDetails trainingDetails);

    TrainingDetails trainingDetailsDAOToTrainingDetails(TrainingDetailsDAO trainingDetailsDAO);
}
