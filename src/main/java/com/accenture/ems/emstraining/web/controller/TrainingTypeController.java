package com.accenture.ems.emstraining.web.controller;

import com.accenture.ems.emstraining.business.service.TrainingTypeService;
import com.accenture.ems.emstraining.models.TrainingType;
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

@Api(tags = {DescriptionVariables.TrainingTypes})
//@ApiResponses(value = {
//        @ApiResponse(code = 200, message = "The request has succeeded", response = TrainingType.class, responseContainer = "List"),
//        @ApiResponse(code = 201, message = "The training type is successfully saved"),
//        @ApiResponse(code = 401, message = "The request requires user authentication"),
//        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
//        @ApiResponse(code = 404, message = "The server has not found anything matching the Request-URI"),
//        @ApiResponse(code = 500, message = "Server error")})
@Log4j2
@RestController
@RequestMapping("/api/v1/trainingType")
public class TrainingTypeController {

    @Autowired
    TrainingTypeService trainingTypeService;

    @GetMapping
    @ApiOperation(value = "Finds all training type",
            notes = "Returns the entire list of training type",
            response = TrainingType.class, responseContainer = "List")
    public ResponseEntity<List<TrainingType>> findAllTrainingType() {
        log.info("Retrieve list of training type");
        List<TrainingType> trainingTypeList = trainingTypeService.findAllTrainingType();
        if (trainingTypeList.isEmpty()) {
            log.warn("Training type list is empty! {}", trainingTypeList);
            return ResponseEntity.notFound().build();
        }
        log.debug("Training type list is found. Size: {}", trainingTypeList::size);
        return ResponseEntity.ok(trainingTypeList);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find training type by id",
            notes = "Provide an id to search specific training type in database",
            response = TrainingType.class)
    public ResponseEntity<TrainingType> findTrainingTypeById(@ApiParam(value = "id of the training type", required = true)
                                                     @NonNull @PathVariable Long id) {
        log.info("Find training type by passing ID, where training ID is :{} ", id);
        Optional<TrainingType> trainingType = (trainingTypeService.findTrainingTypeById(id));
        if (!trainingType.isPresent()) {
            log.warn("Training type with id {} is not found.", id);
        } else {
            log.debug("Training type with id {} is found: {}", id, trainingType);
        }
        return trainingType.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ApiOperation(value = "Saves the training type in database",
            notes = "If provided valid training type, saves it",
            response = TrainingType.class)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TrainingType> saveTrainingType(@Valid @RequestBody TrainingType trainingType, BindingResult bindingResult) throws Exception {
        log.info("Create new training type by passing : {}", trainingType);
        if (bindingResult.hasErrors()) {
            log.error("New training type is not created: error {}", bindingResult);
            return ResponseEntity.badRequest().build();
        }
        TrainingType trainingTypeSaved = trainingTypeService.saveTrainingType(trainingType);
        log.debug("New training type is created: {}", trainingType);
        return new ResponseEntity<>(trainingTypeSaved, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletes the Training Type by id",
            notes = "Deletes the Training Type if provided id exists",
            response = TrainingType.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteTrainingTypeById(@ApiParam(value = "The id of the Training Type", required = true)
                                                   @NonNull @PathVariable Long id) {
        log.info("Delete Training Type by passing ID, where ID is:{}", id);
        Optional<TrainingType> trainingType = trainingTypeService.findTrainingTypeById(id);
        if (!(trainingType.isPresent())) {
            log.warn("Training Type for delete with id {} is not found.", id);
            return ResponseEntity.notFound().build();
        }
        trainingTypeService.deleteTrainingTypeById(id);
        log.debug("Training Type with id {} is deleted: {}", id, trainingType);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
