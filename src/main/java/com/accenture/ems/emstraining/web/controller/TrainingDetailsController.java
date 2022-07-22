package com.accenture.ems.emstraining.web.controller;

import com.accenture.ems.emstraining.business.service.TrainingDetailsService;
import com.accenture.ems.emstraining.models.TrainingDetails;
import com.accenture.ems.emstraining.swagger.DescriptionVariables;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

@Api(tags = {DescriptionVariables.TRAINING_DETAILS})
//@ApiResponses(value = {
//        @ApiResponse(code = 200, message = "The request has succeeded", response = TrainingDetails.class, responseContainer = "List"),
//        @ApiResponse(code = 201, message = "The training details is successfully saved"),
//        @ApiResponse(code = 401, message = "The request requires user authentication"),
//        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
//        @ApiResponse(code = 404, message = "The server has not found anything matching the Request-URI"),
//        @ApiResponse(code = 500, message = "Server error")})
@Log4j2
@RestController
@RequestMapping("/api/v1/trainingDetails")
public class TrainingDetailsController {

    @Autowired
    TrainingDetailsService trainingDetailsService;

    @GetMapping
    @ApiOperation(value = "Finds all training details",
            notes = "Returns the entire list of training details ",
            response = TrainingDetails.class, responseContainer = "List")
    public ResponseEntity<List<TrainingDetails>> findAllTrainingDetails() {
        log.info("Retrieve list of training details");
        List<TrainingDetails> trainingDetailsList = trainingDetailsService.findAllTrainingDetails();
        if (trainingDetailsList.isEmpty()) {
            log.warn("Training details list is empty! {}", trainingDetailsList);
            return ResponseEntity.notFound().build();
        }
        log.debug("Training Details list is found. Size: {}", trainingDetailsList::size);
        return ResponseEntity.ok(trainingDetailsList);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find training details by id",
            notes = "Provide an id to search specific training details in database",
            response = TrainingDetails.class)
    public ResponseEntity<TrainingDetails> findTrainingDetailsById(@ApiParam(value = "id of the training details", required = true)
                                                 @NonNull @PathVariable Long id) {
        log.info("Find training details by passing ID, where training details ID is :{} ", id);
        Optional<TrainingDetails> trainingDetails = (trainingDetailsService.findTrainingDetailsById(id));
        if (!trainingDetails.isPresent()) {
            log.warn("Training details with id {} is not found.", id);
        } else {
            log.debug("Training details with id {} is found: {}", id, trainingDetails);
        }
        return trainingDetails.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ApiOperation(value = "Saves the training Details in database",
            notes = "If provided valid training Details, saves it",
            response = TrainingDetails.class)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TrainingDetails> saveTrainingDetails(@Valid @RequestBody TrainingDetails trainingDetails, BindingResult bindingResult) throws Exception {
        log.info("Create new training Details by passing : {}", trainingDetails);
        if (bindingResult.hasErrors()) {
            log.error("New training Details is not created: error {}", bindingResult);
            return ResponseEntity.badRequest().build();
        }
        TrainingDetails trainingDetailsSaved = trainingDetailsService.saveTrainingDetails(trainingDetails);
        log.debug("New training Details is created: {}", trainingDetails);
        return new ResponseEntity<>(trainingDetailsSaved, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletes the Training details by id",
            notes = "Deletes the Training details if provided id exists",
            response = TrainingDetails.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteTrainingDetailsById(@ApiParam(value = "The id of the Training details", required = true)
                                                       @NonNull @PathVariable Long id) {
        log.info("Delete Training details by passing ID, where ID is:{}", id);
        Optional<TrainingDetails> trainingDetails = trainingDetailsService.findTrainingDetailsById(id);
        if (!(trainingDetails.isPresent())) {
            log.warn("Training details for delete with id {} is not found.", id);
            return ResponseEntity.notFound().build();
        }
        trainingDetailsService.deleteTrainingDetailsById(id);
        log.debug("Training details with id {} is deleted: {}", id, trainingDetails);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
