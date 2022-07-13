package com.accenture.ems.emstraining.web.controller;

import com.accenture.ems.emstraining.business.service.TrainingService;
import com.accenture.ems.emstraining.models.Training;
import com.accenture.ems.emstraining.models.TrainingType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TrainingController.class)
class TrainingControllerTest {

    public static String URL = "/api/v1/training";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TrainingController controller;

    @MockBean
    private TrainingService service;


    @Test
    void findAllTraining_successful() throws Exception {
        List<Training> trainingList = createTrainingList();

        when(service.findAllTraining()).thenReturn(trainingList);

        ResultActions mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get(URL))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("testName"))
                .andExpect(status().isOk());

        verify(service, times(1)).findAllTraining();
    }

    @Test
    void findAllTraining_invalid() throws Exception {
        List<Training> trainingList = createTrainingList();
        trainingList.clear();

        when(service.findAllTraining()).thenReturn(trainingList);

        ResultActions mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get(URL)
                        .content(asJsonString(trainingList))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(service, times(1)).findAllTraining();
    }

    @Test
    void findTrainingById_successful() throws Exception {
        Optional<Training> training = Optional.of(createTraining(1L));

        when(service.findTrainingById(anyLong())).thenReturn(training);

        ResultActions mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get(URL + "/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("testName"))
                .andExpect(status().isOk());

        verify(service, times(1)).findTrainingById(anyLong());
    }

    @Test
    void findTrainingById_invalid() throws Exception {
        Optional<Training> training = Optional.of(createTraining(1L));
        training.get().setId(null);

        when(service.findTrainingById(null)).thenReturn(Optional.empty());

        ResultActions mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get(URL + null)
                        .content(asJsonString(training))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(service, times(0)).findTrainingById(null);
    }

    @Test
    void saveTraining_successful() throws Exception {
        Training training = createTraining(1L);
        training.setId(null);

        when(service.saveTraining(training)).thenReturn(training);

        ResultActions mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .post(URL)
                        .content(asJsonString(training))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        verify(service, times(1)).saveTraining(training);
    }

    @Test
    void saveTraining_invalid() throws Exception {
        Training training = createTraining(1L);
        training.setName(null);

        ResultActions mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .post(URL)
                        .content(asJsonString(training))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

        verify(service, times(0)).saveTraining(training);
    }

    @Test
    void deleteTrainingById_successful() throws Exception {
        Optional<Training> training = Optional.of(createTraining(1L));
        when(service.findTrainingById(anyLong())).thenReturn(training);

        ResultActions mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .delete(URL + "/1")
                        .content(asJsonString(training))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(service, times(1)).deleteTrainingById(anyLong());
    }

    @Test
    void deleteTrainingById_invalid() throws Exception {
        Optional<Training> training = Optional.of(createTraining(1L));
        training.get().setId(null);

        when(service.findTrainingById(null)).thenReturn(training);

        ResultActions mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .delete(URL + null)
                        .content(asJsonString(training))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(service, times(0)).deleteTrainingById(null);
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

    private List<Training> createTrainingList() {
        List<Training> trainingList = new ArrayList<>();
        trainingList.add(createTraining(1L));
        trainingList.add(createTraining(2L));
        return trainingList;
    }

    private TrainingType createTrainingType(Long id){
        TrainingType trainingType =new TrainingType();
        trainingType.setId(id);
        trainingType.setType("testName");
        return trainingType;
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}