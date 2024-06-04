package cz.tomas.matikaapi.controller;

import cz.tomas.matikaapi.configuration.AppConfiguration;
import cz.tomas.matikaapi.dto.AvailableMathTaskTypes;
import cz.tomas.matikaapi.dto.MathOperationTypes;
import cz.tomas.matikaapi.dto.MathTaskInstructions;
import cz.tomas.matikaapi.dto.MathTasks;
import cz.tomas.matikaapi.dto.constants.MatikaAPIConstants;
import cz.tomas.matikaapi.dto.requests.*;
import cz.tomas.matikaapi.services.impl.MainTaskGenerator;
import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(
        name = "Math Assignments"
)
@Slf4j
public class InputTaskController {

    private final MainTaskGenerator mainTaskGenerator;
    private final AppConfiguration appConfiguration;

    public InputTaskController(MainTaskGenerator mainTaskGenerator, AppConfiguration appConfiguration) {
        this.mainTaskGenerator = mainTaskGenerator;
        this.appConfiguration = appConfiguration;
    }

    @GetMapping(
            value = MatikaAPIConstants.TASKS_TYPES_PATH,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Returns types of available math tasks",
            description = "Provides the available types of math tasks that the tool is able to produce"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Information provided"),
                    @ApiResponse(responseCode = "500", description = "Internal error has occurred")
            }
    )
    public AvailableMathTaskTypes getAvailableMathTaskTypes(){
        return AvailableMathTaskTypes
                .builder()
                .types(appConfiguration.getAvailableTaskTypes())
                .build();
    }

    @PostMapping(
            value = MatikaAPIConstants.ADDITION_POST_PATH,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Returns addition math assignments",
            description = "Addition assumes two numbers, that, when added together, do not exceed the maxLimit"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Tasks successfully provided"),
                    @ApiResponse(responseCode = "500", description = "Error has occurred during math tasks generation")
            }
    )
    public MathTasks createAddition(
            @RequestBody @Valid InputRequestBody additionRequestBody
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

    @PostMapping(
            value = MatikaAPIConstants.DIVISION_POST_PATH,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Returns division math assignments",
            description = "Dividing numbers into the identical parts - based on the provided values where calling code" +
                    "may specify the min & max for both the first & second number."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Tasks successfully provided"),
                    @ApiResponse(responseCode = "500", description = "Error has occurred during math tasks generation")
            }
    )
    @Timed(value = "division.build", longTask = true)
    public MathTasks createDivision(
            @RequestBody InputRequestBody requestBody
    ) {
        MathTaskInstructions mathTaskInstructions = MathTaskInstructions.builder()
                .mathOperationType(MathOperationTypes.DIVISION)
                .numberOfTasks(requestBody.getTasks())
                .maxFirstValue(requestBody.getFirstValue().getMaxValue())
                .maxSecondValue(requestBody.getSecondValue().getMaxValue())
                .maxLimit(requestBody.getMaxResult())
                .firstValue(requestBody.getFirstValue())
                .secondValue(requestBody.getSecondValue())
                .build();
        return mainTaskGenerator.buildTask(mathTaskInstructions);
    }

    @PostMapping(
            value = MatikaAPIConstants.MULTIPLICATION_POST_PATH,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Returns multiplication math assignments"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Tasks successfully provided"),
                    @ApiResponse(responseCode = "500", description = "Error has occurred during math tasks generation")
            }
    )
    public MathTasks createMultiplication(
            @RequestBody @Valid InputRequestBody requestBody
    ) {
        MathTaskInstructions mathTaskInstructions = MathTaskInstructions.builder()
                .mathOperationType(MathOperationTypes.MULTIPLICATION)
                .numberOfTasks(requestBody.getTasks())
                .maxFirstValue(requestBody.getFirstValue().getMaxValue())
                .maxSecondValue(requestBody.getSecondValue().getMaxValue())
                .maxLimit(requestBody.getMaxResult())
                .firstValue(requestBody.getFirstValue())
                .secondValue(requestBody.getSecondValue())
                .build();
        return mainTaskGenerator.buildTask(mathTaskInstructions);
    }

    @PostMapping(
            value = MatikaAPIConstants.SUBTRACTION_POST_PATH,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Returns subtraction math assignments"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Tasks successfully provided"),
                    @ApiResponse(responseCode = "500", description = "Error has occurred during math tasks generation")
            }
    )
    public MathTasks createSubtractions(
            @RequestBody @Valid InputRequestBody requestBody
    ) {
        log.info("Received request...");
        MathTaskInstructions mathTaskInstructions = MathTaskInstructions.builder()
                .mathOperationType(MathOperationTypes.SUBTRACTION)
                .numberOfTasks(requestBody.getTasks())
                .maxFirstValue(requestBody.getFirstValue().getMaxValue())
                .maxSecondValue(requestBody.getSecondValue().getMaxValue())
                .maxLimit(requestBody.getMaxResult())
                .firstValue(requestBody.getFirstValue())
                .secondValue(requestBody.getSecondValue())
                .build();
        return mainTaskGenerator.buildTask(mathTaskInstructions);
    }
}
