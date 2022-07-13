package com.accenture.ems.emstraining.web.controller;

import com.accenture.ems.emstraining.business.service.TrainingService;
import com.accenture.ems.emstraining.models.Training;
import com.accenture.ems.emstraining.swagger.DescriptionVariables;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Api(tags = {DescriptionVariables.TRAINING})
@Log4j2
@RestController
@RequestMapping("/api/v1/training")
public class TrainingController {

    @Autowired
    TrainingService trainingService;

    @GetMapping
    @ApiOperation(value = "Finds all training",
            notes = "Returns the entire list of training",
            response = Training.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded", response = Training.class, responseContainer = "List"),
            @ApiResponse(code = 401, message = "The request requires user authentication"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The server has not found anything matching the Request-URI"),
            @ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<List<Training>> findAllTraining() {
        log.info("Retrieve list of training");
        List<Training> trainingList = trainingService.findAllTraining();
        if (trainingList.isEmpty()) {
            log.warn("Training list is empty! {}", trainingList);
            return ResponseEntity.notFound().build();
        }
        log.debug("Training list is found. Size: {}", trainingList::size);
        return ResponseEntity.ok(trainingList);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find training by id",
            notes = "Provide an id to search specific training in database",
            response = Training.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded"),
            @ApiResponse(code = 401, message = "The request requires user authentication"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The server has not found anything matching the Request-URI"),
            @ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<Training> findTrainingById(@ApiParam(value = "id of the training", required = true)
                                                          @NonNull @PathVariable Long id) {
        log.info("Find training by passing ID, where training ID is :{} ", id);
        Optional<Training> training = (trainingService.findTrainingById(id));
        if (!training.isPresent()) {
            log.warn("Training with id {} is not found.", id);
        } else {
            log.debug("Training with id {} is found: {}", id, training);
        }
        return training.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ApiOperation(value = "Saves the training in database",
            notes = "If provided valid training, saves it",
            response = Training.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The training is successfully saved"),
            @ApiResponse(code = 400, message = "Missed required parameters, parameters are not valid"),
            @ApiResponse(code = 401, message = "The request requires user authentication"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The server has not found anything matching the Request-URI"),
            @ApiResponse(code = 500, message = "Server error")}
    )
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Training> saveTraining(@Valid @RequestBody Training training, BindingResult bindingResult) throws Exception {
        log.info("Create new training by passing : {}", training);
        if (bindingResult.hasErrors()) {
            log.error("New training is not created: error {}", bindingResult);
            return ResponseEntity.badRequest().build();
        }
        Training trainingSaved = trainingService.saveTraining(training);
        log.debug("New training is created: {}", training);
        return new ResponseEntity<>(trainingSaved, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletes the Training  by id",
            notes = "Deletes the Training if provided id exists",
            response = Training.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "The Training is successfully deleted"),
            @ApiResponse(code = 401, message = "The request requires user authentication"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The server has not found anything matching the Request-URI"),
            @ApiResponse(code = 500, message = "Server error")})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteTrainingById(@ApiParam(value = "The id of the Training ", required = true)
                                                       @NonNull @PathVariable Long id) {
        log.info("Delete Training by passing ID, where ID is:{}", id);
        Optional<Training> training = trainingService.findTrainingById(id);
        if (!(training.isPresent())) {
            log.warn("Training for delete with id {} is not found.", id);
            return ResponseEntity.notFound().build();
        }
        trainingService.deleteTrainingById(id);
        log.debug("Training with id {} is deleted: {}", id, training);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
