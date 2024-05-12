package cz.tomas.matikaapi.controller;

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

    public InputTaskController(MainTaskGenerator mainTaskGenerator) {
        this.mainTaskGenerator = mainTaskGenerator;
    }

    @PostMapping(
            value = MatikaAPIConstants.ADDITION_POST_PATH,
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
            summary = "Returns division math assignments"
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
