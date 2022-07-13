package com.accenture.ems.emstraining.web.controller;

import com.accenture.ems.emstraining.business.service.TrainingTypeService;
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

@WebMvcTest(TrainingTypeController.class)
class TrainingTypeControllerTest {

    public static String URL = "/api/v1/trainingType";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TrainingTypeController controller;

    @MockBean
    private TrainingTypeService service;

    @Test
    void findAllTrainingType_successful() throws Exception {
        List<TrainingType> trainingTypeList = createTrainingTypeList();

        when(service.findAllTrainingType()).thenReturn(trainingTypeList);

        ResultActions mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get(URL))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].type").value("testName"))
                .andExpect(status().isOk());

        verify(service, times(1)).findAllTrainingType();
    }

    @Test
    void findAllTrainingType_invalid() throws Exception {
        List<TrainingType> trainingTypeList = createTrainingTypeList();
        trainingTypeList.clear();

        when(service.findAllTrainingType()).thenReturn(trainingTypeList);

        ResultActions mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get(URL)
                        .content(asJsonString(trainingTypeList))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(service, times(1)).findAllTrainingType();
    }

    @Test
    void findTrainingTypeById_successful() throws Exception {
        Optional<TrainingType> trainingType = Optional.of(createTrainingType(1L));

        when(service.findTrainingTypeById(anyLong())).thenReturn(trainingType);

        ResultActions mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get(URL + "/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.type").value("testName"))
                .andExpect(status().isOk());

        verify(service, times(1)).findTrainingTypeById(anyLong());
    }

    @Test
    void findTrainingTypeById_invalid() throws Exception {
        Optional<TrainingType> trainingType = Optional.of(createTrainingType(1L));
        trainingType.get().setId(null);

        when(service.findTrainingTypeById(null)).thenReturn(Optional.empty());

        ResultActions mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get(URL + null)
                        .content(asJsonString(trainingType))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(service, times(0)).findTrainingTypeById(null);
    }

    @Test
    void saveTrainingType_successful() throws Exception {
        TrainingType trainingType = createTrainingType(1L);
        trainingType.setId(null);

        when(service.saveTrainingType(trainingType)).thenReturn(trainingType);

        ResultActions mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .post(URL)
                        .content(asJsonString(trainingType))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        verify(service, times(1)).saveTrainingType(trainingType);
    }

    @Test
    void saveTrainingType_invalid() throws Exception {
        TrainingType trainingType = createTrainingType(1L);
        trainingType.setType(null);

        ResultActions mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .post(URL)
                        .content(asJsonString(trainingType))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

        verify(service, times(0)).saveTrainingType(trainingType);
    }

    @Test
    void deleteTrainingTypeById_successful() throws Exception {
        Optional<TrainingType> trainingType = Optional.of(createTrainingType(1L));
        when(service.findTrainingTypeById(anyLong())).thenReturn(trainingType);

        ResultActions mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .delete(URL + "/1")
                        .content(asJsonString(trainingType))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(service, times(1)).deleteTrainingTypeById(anyLong());
    }

    @Test
    void deleteTrainingTypeById_invalid() throws Exception {
        Optional<TrainingType> trainingType = Optional.of(createTrainingType(1L));
        trainingType.get().setId(null);

        when(service.findTrainingTypeById(null)).thenReturn(trainingType);

        ResultActions mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .delete(URL + null)
                        .content(asJsonString(trainingType))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(service, times(0)).deleteTrainingTypeById(null);
    }

    private TrainingType createTrainingType(Long id){
        TrainingType trainingType =new TrainingType();
        trainingType.setId(id);
        trainingType.setType("testName");
        return trainingType;
    }

    private List<TrainingType> createTrainingTypeList() {
        List<TrainingType> trainingTypeList = new ArrayList<>();
        trainingTypeList.add(createTrainingType(1L));
        trainingTypeList.add(createTrainingType(2L));
        return trainingTypeList;
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}