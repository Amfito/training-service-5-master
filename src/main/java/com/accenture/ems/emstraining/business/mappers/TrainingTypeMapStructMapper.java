package com.accenture.ems.emstraining.business.mappers;


import com.accenture.ems.emstraining.business.repository.model.TrainingTypeDAO;
import com.accenture.ems.emstraining.models.TrainingType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring"/*, uses = {TrainingDetailsMapStructMapper.class}*/)
public interface TrainingTypeMapStructMapper {

    TrainingTypeDAO trainingTypeToTrainingTypeDAO(TrainingType trainingType);

    TrainingType trainingTypeDAOToTrainingType(TrainingTypeDAO trainingTypeDAO);
}
