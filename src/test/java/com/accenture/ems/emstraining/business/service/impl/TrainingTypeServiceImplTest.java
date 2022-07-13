package com.accenture.ems.emstraining.business.service.impl;

import com.accenture.ems.emstraining.business.mappers.TrainingTypeMapStructMapper;
import com.accenture.ems.emstraining.business.repository.TrainingTypeRepository;
import com.accenture.ems.emstraining.business.repository.model.TrainingTypeDAO;
import com.accenture.ems.emstraining.models.TrainingType;
import org.junit.Before;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class TrainingTypeServiceImplTest {

    @Mock
    private TrainingTypeRepository repository;

    @InjectMocks
    private TrainingTypeServiceImpl service;

    @Mock
    private TrainingTypeMapStructMapper mapper;

    private TrainingType trainingType;
    private TrainingTypeDAO trainingTypeDAO;
    private List<TrainingType> trainingTypeList;
    private List<TrainingTypeDAO> trainingTypeDAOList;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllTrainingType_successful() {
        trainingTypeDAOList = createTrainingTypeDAOList();
        trainingTypeList = createTrainingTypeList();

        when(repository.findAll()).thenReturn(trainingTypeDAOList);
        List<TrainingType> trainings = service.findAllTrainingType();
        assertEquals(2, trainings.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void findAllTrainingType_invalid() {
        when(repository.findAll()).thenReturn(Collections.emptyList());
        Assertions.assertTrue(service.findAllTrainingType().isEmpty());
        verify(repository, times(1)).findAll();
    }

    @Test
    void findTrainingTypeById_successful() {
        trainingType = createTrainingType(1L);
        trainingTypeDAO = createTrainingTypeDAO(1L);

        when(repository.findById(anyLong())).thenReturn(Optional.of(trainingTypeDAO));
        when(mapper.trainingTypeDAOToTrainingType(trainingTypeDAO)).thenReturn(trainingType);
        Optional<TrainingType> returnedTrainingType = service.findTrainingTypeById(trainingType.getId());
        assertEquals(trainingType.getId(), returnedTrainingType.get().getId());
        assertEquals(trainingType.getType(), returnedTrainingType.get().getType());
        verify(repository, times(1)).findById(anyLong());
    }

    @Test
    void findTrainingTypeById_invalid() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        Assertions.assertFalse(service.findTrainingTypeById(anyLong()).isPresent());
        verify(repository, times(1)).findById(anyLong());
    }

    @Test
    void saveTrainingType_successful() throws Exception {
        when(repository.save(trainingTypeDAO)).thenReturn(trainingTypeDAO);
        when(mapper.trainingTypeDAOToTrainingType(trainingTypeDAO)).thenReturn(trainingType);
        when(mapper.trainingTypeToTrainingTypeDAO(trainingType)).thenReturn(trainingTypeDAO);
        TrainingType trainingTypeSaved = service.saveTrainingType(trainingType);
        assertEquals(trainingType, trainingTypeSaved);
        verify(repository, times(1)).save(trainingTypeDAO);
    }

    @Test
    void saveTrainingType_invalid() throws Exception {
        when(repository.save(trainingTypeDAO)).thenThrow(new IllegalArgumentException());
        when(mapper.trainingTypeToTrainingTypeDAO(trainingType)).thenReturn(trainingTypeDAO);
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.saveTrainingType(trainingType));
        verify(repository, times(1)).save(trainingTypeDAO);
    }

    @Test
    void deleteTrainingTypeById_successful() {
        service.deleteTrainingTypeById(anyLong());
        verify(repository, times(1)).deleteById(anyLong());
    }

    @Test
    void deleteTrainingTypeById_invalid() {
        doThrow(new IllegalArgumentException()).when(repository).deleteById(anyLong());
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> service.deleteTrainingTypeById(anyLong()));
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

    private List<TrainingType> createTrainingTypeList() {
        List<TrainingType> trainingTypeList = new ArrayList<>();
        trainingTypeList.add(createTrainingType(1L));
        trainingTypeList.add(createTrainingType(2L));
        return trainingTypeList;
    }

    private List<TrainingTypeDAO> createTrainingTypeDAOList() {
        List<TrainingTypeDAO> trainingTypeDAOList = new ArrayList<>();
        trainingTypeDAOList.add(createTrainingTypeDAO(1L));
        trainingTypeDAOList.add(createTrainingTypeDAO(2L));
        return trainingTypeDAOList;
    }
}