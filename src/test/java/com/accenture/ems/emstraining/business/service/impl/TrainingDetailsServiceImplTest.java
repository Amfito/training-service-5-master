package com.accenture.ems.emstraining.business.service.impl;

import com.accenture.ems.emstraining.business.mappers.TrainingDetailsMapStructMapper;
import com.accenture.ems.emstraining.business.repository.TrainingDetailsRepository;
import com.accenture.ems.emstraining.business.repository.model.TrainingDAO;
import com.accenture.ems.emstraining.business.repository.model.TrainingDetailsDAO;
import com.accenture.ems.emstraining.business.repository.model.TrainingTypeDAO;
import com.accenture.ems.emstraining.models.Training;
import com.accenture.ems.emstraining.models.TrainingDetails;
import com.accenture.ems.emstraining.models.TrainingType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
@AutoConfigureMockMvc
class TrainingDetailsServiceImplTest {

    @Mock
    private TrainingDetailsRepository repository;

    @InjectMocks
    private TrainingDetailsServiceImpl service;

    @Mock
    private TrainingDetailsMapStructMapper mapper;

    private TrainingDetails trainingDetails;
    private TrainingDetailsDAO trainingDetailsDAO;
    private List<TrainingDetails> trainingDetailsList;
    private List<TrainingDetailsDAO> trainingDetailsDAOList;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllTrainingDetails_successful() {
        trainingDetailsDAOList = createTrainingDetailsDAOList();
        trainingDetailsList = createTrainingDetailsList();

        when(repository.findAll()).thenReturn(trainingDetailsDAOList);
        List<TrainingDetails> trainingsDetails = service.findAllTrainingDetails();
        assertEquals(2, trainingsDetails.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void findAllTrainingDetails_invalid() {
        when(repository.findAll()).thenReturn(Collections.emptyList());
        Assertions.assertTrue(service.findAllTrainingDetails().isEmpty());
        verify(repository, times(1)).findAll();
    }

    @Test
    void findTrainingDetailsById_successful() {
        trainingDetails = createTrainingDetails(1L);
        trainingDetailsDAO = createTrainingDetailsDAO(1L);

        when(repository.findById(anyLong())).thenReturn(Optional.of(trainingDetailsDAO));
        when(mapper.trainingDetailsDAOToTrainingDetails(trainingDetailsDAO)).thenReturn(trainingDetails);
        Optional<TrainingDetails> returnedTrainingDetails = service.findTrainingDetailsById(trainingDetails.getId());
        assertEquals(trainingDetails.getId(), returnedTrainingDetails.get().getId());
        assertEquals(trainingDetails.getEmployee(), returnedTrainingDetails.get().getEmployee());
        assertEquals(trainingDetails.getStartDate(), returnedTrainingDetails.get().getStartDate());
        verify(repository, times(1)).findById(anyLong());
    }

    @Test
    void findTrainingDetailsById_invalid() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        Assertions.assertFalse(service.findTrainingDetailsById(anyLong()).isPresent());
        verify(repository, times(1)).findById(anyLong());
    }

    @Test
    void saveTrainingDetails_successful() throws Exception {
        when(repository.save(trainingDetailsDAO)).thenReturn(trainingDetailsDAO);
        when(mapper.trainingDetailsDAOToTrainingDetails(trainingDetailsDAO)).thenReturn(trainingDetails);
        when(mapper.trainingDetailsToTrainingDetailsDAO(trainingDetails)).thenReturn(trainingDetailsDAO);
        TrainingDetails trainingDetailsSaved = service.saveTrainingDetails(trainingDetails);
        assertEquals(trainingDetails, trainingDetailsSaved);
        verify(repository, times(1)).save(trainingDetailsDAO);
    }

    @Test
    void saveTrainingDetails_invalid() {
        when(repository.save(trainingDetailsDAO)).thenThrow(new IllegalArgumentException());
        when(mapper.trainingDetailsToTrainingDetailsDAO(trainingDetails)).thenReturn(trainingDetailsDAO);
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.saveTrainingDetails(trainingDetails));
        verify(repository, times(1)).save(trainingDetailsDAO);
    }

    @Test
    void deleteTrainingTypeById_successful() {
        service.deleteTrainingDetailsById(anyLong());
        verify(repository, times(1)).deleteById(anyLong());
    }

    @Test
    void deleteTrainingTypeById_invalid() {
        doThrow(new IllegalArgumentException()).when(repository).deleteById(anyLong());
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> service.deleteTrainingDetailsById(anyLong()));
    }

    private TrainingDetails createTrainingDetails(Long id){
        TrainingDetails trainingDetails =new TrainingDetails();
        trainingDetails.setId(id);
        trainingDetails.setTraining(createTraining(1L));
        trainingDetails.setEmployee(1L);
        trainingDetails.setEmployeeRelation(1L);
        trainingDetails.setFinalPresentationDate("2022-10-30");
        trainingDetails.setStartDate("2022-10-10");
        trainingDetails.setEndDate("2022-11-11");
        return trainingDetails;
    }

    private TrainingDetailsDAO createTrainingDetailsDAO(Long id){
        TrainingDetailsDAO trainingDetailsDAO =new TrainingDetailsDAO();
        trainingDetailsDAO.setId(id);
        trainingDetailsDAO.setTraining(createTrainingDAO(1L));
        trainingDetailsDAO.setEmployee(1L);
        trainingDetailsDAO.setEmployeeRelation(1L);
        trainingDetailsDAO.setFinalPresentationDate("2022-10-30");
        trainingDetailsDAO.setStartDate("2022-10-10");
        trainingDetailsDAO.setEndDate("2022-11-11");
        return trainingDetailsDAO;
    }

    private List<TrainingDetails> createTrainingDetailsList() {
        List<TrainingDetails> trainingDetailsList = new ArrayList<>();
        trainingDetailsList.add(createTrainingDetails(1L));
        trainingDetailsList.add(createTrainingDetails(2L));
        return trainingDetailsList;
    }

    private List<TrainingDetailsDAO> createTrainingDetailsDAOList() {
        List<TrainingDetailsDAO> trainingDetailsDAOList = new ArrayList<>();
        trainingDetailsDAOList.add(createTrainingDetailsDAO(1L));
        trainingDetailsDAOList.add(createTrainingDetailsDAO(2L));
        return trainingDetailsDAOList;
    }

    private Training createTraining(Long id){
        Training training =new Training();
        training.setId(id);
        training.setName("testName");
        training.setTrainingType(createTrainingType(1L));
        training.setStartDate("2022-10-10");
        training.setEndDate("2022-11-11");
        return training;
    }

    private TrainingDAO createTrainingDAO(Long id){
        TrainingDAO trainingDAO =new TrainingDAO();
        trainingDAO.setId(id);
        trainingDAO.setName("testName");
        trainingDAO.setTrainingType(createTrainingTypeDAO(1L));
        trainingDAO.setStartDate("2022-10-10");
        trainingDAO.setEndDate("2022-11-11");
        return trainingDAO;
    }

    private TrainingType createTrainingType(Long id){
        TrainingType trainingType =new TrainingType();
        trainingType.setId(id);
        trainingType.setType("testName");
        return trainingType;
    }

    private TrainingTypeDAO createTrainingTypeDAO(Long id){
        TrainingTypeDAO trainingTypeDAO =new TrainingTypeDAO();
        trainingTypeDAO.setId(id);
        trainingTypeDAO.setType("testName");
        return trainingTypeDAO;
    }
}