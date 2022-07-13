package com.accenture.ems.emstraining.web.controller;

import com.accenture.ems.emstraining.business.service.TrainingDetailsService;
import com.accenture.ems.emstraining.models.Training;
import com.accenture.ems.emstraining.models.TrainingDetails;
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

@WebMvcTest(TrainingDetailsController.class)
class TrainingDetailsControllerTest {

    public static String URL = "/api/v1/trainingDetails";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TrainingDetailsController controller;

    @MockBean
    private TrainingDetailsService service;

    @Test
    void findAllTrainingDetails_successful() throws Exception {
        List<TrainingDetails> trainingDetailsList = createTrainingDetailsList();

        when(service.findAllTrainingDetails()).thenReturn(trainingDetailsList);

        ResultActions mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get(URL))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].employee").value(1L))
                .andExpect(status().isOk());

        verify(service, times(1)).findAllTrainingDetails();
    }

    @Test
    void findAllTrainingDetails_invalid() throws Exception {
        List<TrainingDetails> trainingDetailsList = createTrainingDetailsList();
        trainingDetailsList.clear();

        when(service.findAllTrainingDetails()).thenReturn(trainingDetailsList);

        ResultActions mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get(URL)
                        .content(asJsonString(trainingDetailsList))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(service, times(1)).findAllTrainingDetails();
    }

    @Test
    void findTrainingDetailsById_successful() throws Exception {
        Optional<TrainingDetails> trainingDetails = Optional.of(createTrainingDetails(1L));

        when(service.findTrainingDetailsById(anyLong())).thenReturn(trainingDetails);

        ResultActions mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get(URL + "/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.employee").value(1L))
                .andExpect(status().isOk());

        verify(service, times(1)).findTrainingDetailsById(anyLong());
    }

    @Test
    void findTrainingDetailsById_invalid() throws Exception {
        Optional<TrainingDetails> trainingDetails = Optional.of(createTrainingDetails(1L));
        trainingDetails.get().setId(null);

        when(service.findTrainingDetailsById(null)).thenReturn(Optional.empty());

        ResultActions mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get(URL + null)
                        .content(asJsonString(trainingDetails))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(service, times(0)).findTrainingDetailsById(null);
    }

    @Test
    void saveTrainingDetails_successful() throws Exception {
        TrainingDetails trainingDetails = createTrainingDetails(1L);
        trainingDetails.setId(null);

        when(service.saveTrainingDetails(trainingDetails)).thenReturn(trainingDetails);

        ResultActions mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .post(URL)
                        .content(asJsonString(trainingDetails))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        verify(service, times(1)).saveTrainingDetails(trainingDetails);
    }

    @Test
    void saveTrainingDetails_invalid() throws Exception {
        TrainingDetails trainingDetails = createTrainingDetails(1L);
        trainingDetails.setEmployee(null);

        ResultActions mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .post(URL)
                        .content(asJsonString(trainingDetails))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

        verify(service, times(0)).saveTrainingDetails(trainingDetails);
    }

    @Test
    void deleteTrainingDetailsById_successful() throws Exception {
        Optional<TrainingDetails> trainingDetails = Optional.of(createTrainingDetails(1L));
        when(service.findTrainingDetailsById(anyLong())).thenReturn(trainingDetails);

        ResultActions mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .delete(URL + "/1")
                        .content(asJsonString(trainingDetails))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(service, times(1)).deleteTrainingDetailsById(anyLong());
    }

    @Test
    void deleteTrainingDetailsById_invalid() throws Exception {
        Optional<TrainingDetails> trainingDetails = Optional.of(createTrainingDetails(1L));
        trainingDetails.get().setId(null);

        when(service.findTrainingDetailsById(null)).thenReturn(trainingDetails);

        ResultActions mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .delete(URL + null)
                        .content(asJsonString(trainingDetails))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(service, times(0)).deleteTrainingDetailsById(null);
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

    private List<TrainingDetails> createTrainingDetailsList() {
        List<TrainingDetails> trainingDetailsList = new ArrayList<>();
        trainingDetailsList.add(createTrainingDetails(1L));
        trainingDetailsList.add(createTrainingDetails(2L));
        return trainingDetailsList;
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