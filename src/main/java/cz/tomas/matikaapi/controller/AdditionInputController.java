package cz.tomas.matikaapi.controller;

import cz.tomas.matikaapi.dto.requests.AdditionRequestBody;
import cz.tomas.matikaapi.dto.MathOperationTypes;
import cz.tomas.matikaapi.dto.MathTaskInstructions;
import cz.tomas.matikaapi.dto.MathTasks;
import cz.tomas.matikaapi.services.impl.MainTaskGenerator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Tag(
        name = "Addition Math Assignment"
)
public class AdditionInputController {

    @Autowired
    public AdditionInputController(MainTaskGenerator mainTaskGenerator) {
        this.mainTaskGenerator = mainTaskGenerator;
    }

    MainTaskGenerator mainTaskGenerator;

    @PostMapping(
            value = "/post/scitani",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Returns addition math assignments"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Tasks successfully provided"),
                    @ApiResponse(responseCode = "500", description = "Error has occurred during math tasks generation")
            }
    )
    public MathTasks createAddition(
            @RequestBody @Valid AdditionRequestBody additionRequestBody
            ){
        MathTaskInstructions mathTaskInstructions = MathTaskInstructions.builder()
                .mathOperationType(MathOperationTypes.ADDITION)
                .numberOfTasks(additionRequestBody.getTasks())
                .maxFirstValue(additionRequestBody.getFirstValue().getMaxValue())
                .maxSecondValue(additionRequestBody.getSecondValue().getMaxValue())
                .maxLimit(additionRequestBody.getMaxResult())
                .firstValue(additionRequestBody.getFirstValue())
                .secondValue(additionRequestBody.getSecondValue())
                .build();
        return mainTaskGenerator.buildTask(mathTaskInstructions);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
