package com.accenture.ems.emstraining.business.service.impl;

import com.accenture.ems.emstraining.business.mappers.TrainingMapStructMapper;
import com.accenture.ems.emstraining.business.repository.TrainingRepository;
import com.accenture.ems.emstraining.business.repository.model.TrainingDAO;
import com.accenture.ems.emstraining.business.repository.model.TrainingTypeDAO;
import com.accenture.ems.emstraining.models.Training;
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
class TrainingServiceImplTest {

    @Mock
    private TrainingRepository repository;

    @InjectMocks
    private TrainingServiceImpl service;

    @Mock
    private TrainingMapStructMapper mapper;

    private Training training;
    private TrainingDAO trainingDAO;
    private List<Training> trainingList;
    private List<TrainingDAO> trainingDAOList;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllTraining_successful() {
        trainingDAOList = createTrainingDAOList();
        trainingList = createTrainingList();

        when(repository.findAll()).thenReturn(trainingDAOList);
        List<Training> trainings = service.findAllTraining();
        assertEquals(2, trainings.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void findAllTraining_invalid() {
        when(repository.findAll()).thenReturn(Collections.emptyList());
        Assertions.assertTrue(service.findAllTraining().isEmpty());
        verify(repository, times(1)).findAll();
    }

    @Test
    void findTrainingById_successful() {
        training = createTraining(1L);
        trainingDAO = createTrainingDAO(1L);

        when(repository.findById(anyLong())).thenReturn(Optional.of(trainingDAO));
        when(mapper.trainingDAOToTraining(trainingDAO)).thenReturn(training);
        Optional<Training> returnedTraining = service.findTrainingById(training.getId());
        assertEquals(training.getId(), returnedTraining.get().getId());
        assertEquals(training.getName(), returnedTraining.get().getName());
        verify(repository, times(1)).findById(anyLong());
    }

    @Test
    void findTrainingById_invalid() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        Assertions.assertFalse(service.findTrainingById(anyLong()).isPresent());
        verify(repository, times(1)).findById(anyLong());
    }

    @Test
    void saveTraining_successful() throws Exception {
        when(repository.save(trainingDAO)).thenReturn(trainingDAO);
        when(mapper.trainingDAOToTraining(trainingDAO)).thenReturn(training);
        when(mapper.trainingToTrainingDAO(training)).thenReturn(trainingDAO);
        Training trainingSaved = service.saveTraining(training);
        assertEquals(training, trainingSaved);
        verify(repository, times(1)).save(trainingDAO);
    }

    @Test
    void saveTraining_invalid() {
        when(repository.save(trainingDAO)).thenThrow(new IllegalArgumentException());
        when(mapper.trainingToTrainingDAO(training)).thenReturn(trainingDAO);
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.saveTraining(training));
        verify(repository, times(1)).save(trainingDAO);
    }

    @Test
    void deleteTrainingById_successful() {
        service.deleteTrainingById(anyLong());
        verify(repository, times(1)).deleteById(anyLong());
    }

    @Test
    void deleteTrainingById_invalid() {
        doThrow(new IllegalArgumentException()).when(repository).deleteById(anyLong());
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> service.deleteTrainingById(anyLong()));
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

    private List<Training> createTrainingList() {
        List<Training> trainingList = new ArrayList<>();
        trainingList.add(createTraining(1L));
        trainingList.add(createTraining(2L));
        return trainingList;
    }

    private List<TrainingDAO> createTrainingDAOList() {
        List<TrainingDAO> trainingDAOList = new ArrayList<>();
        trainingDAOList.add(createTrainingDAO(1L));
        trainingDAOList.add(createTrainingDAO(2L));
        return trainingDAOList;
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