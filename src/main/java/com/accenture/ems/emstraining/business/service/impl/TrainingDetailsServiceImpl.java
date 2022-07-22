package com.accenture.ems.emstraining.business.service.impl;

import com.accenture.ems.emstraining.business.mappers.TrainingDetailsMapStructMapper;
import com.accenture.ems.emstraining.business.repository.TrainingDetailsRepository;
import com.accenture.ems.emstraining.business.repository.model.TrainingDAO;
import com.accenture.ems.emstraining.business.repository.model.TrainingDetailsDAO;
import com.accenture.ems.emstraining.business.service.TrainingDetailsService;
import com.accenture.ems.emstraining.models.Training;
import com.accenture.ems.emstraining.models.TrainingDetails;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
public class TrainingDetailsServiceImpl implements TrainingDetailsService {

    @Autowired
    TrainingDetailsRepository trainingDetailsRepository;

    @Autowired
    TrainingDetailsMapStructMapper trainingDetailsMapStructMapper;


    @Override
    public List<TrainingDetails> findAllTrainingDetails() {
        List<TrainingDetailsDAO> trainingDetailsDAOList = trainingDetailsRepository.findAll();
        log.info("Get training details list. Size is: {}", trainingDetailsDAOList::size);
        return trainingDetailsDAOList.stream().map(trainingDetailsMapStructMapper::trainingDetailsDAOToTrainingDetails).collect(Collectors.toList());
    }

    @Override
    public Optional<TrainingDetails> findTrainingDetailsById(Long id) {
        Optional<TrainingDetails> trainingDetailsById = trainingDetailsRepository.findById(id)
                .flatMap(trainingDetails -> Optional.ofNullable(trainingDetailsMapStructMapper.trainingDetailsDAOToTrainingDetails(trainingDetails)));
        log.info("Training Details with id {} is {}", id, trainingDetailsById);
        return trainingDetailsById;
    }

    @Override
    public TrainingDetails saveTrainingDetails(TrainingDetails trainingDetails) throws Exception {
        if (!hasMatch(trainingDetails)) {
            TrainingDetailsDAO trainingDetailsSaved = trainingDetailsRepository.save(trainingDetailsMapStructMapper.trainingDetailsToTrainingDetailsDAO(trainingDetails));
            log.info("New Training Details saved: {}", () -> trainingDetailsSaved);
            return trainingDetailsMapStructMapper.trainingDetailsDAOToTrainingDetails(trainingDetailsSaved);
        }else {
            log.error("Training Details conflict exception is thrown: {}", HttpStatus.CONFLICT);
            throw new HttpClientErrorException(HttpStatus.CONFLICT);
        }
    }

    @Override
    public void deleteTrainingDetailsById(Long id) {
        trainingDetailsRepository.deleteById(id);
        log.info("Training Details with id {} is deleted", id);
    }

    public boolean hasMatch(TrainingDetails trainingDetails){
        return trainingDetailsRepository.findAll().stream().anyMatch(trainingDetailsDAO -> isSame(trainingDetails, trainingDetailsDAO));
    }

    private boolean isSame(TrainingDetails trainingDetails, TrainingDetailsDAO trainingDetailsDAO) {
        return trainingDetailsDAO.getId().equals(trainingDetails.getId()) &&
                trainingDetailsDAO.getEmployee().equals(trainingDetails.getEmployee()) &&
                    trainingDetailsDAO.getEmployeeRelation().equals(trainingDetails.getEmployeeRelation());
    }
}
