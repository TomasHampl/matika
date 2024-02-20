package cz.tomas.matikaapi.controller;

import cz.tomas.matikaapi.dto.*;
import cz.tomas.matikaapi.dto.requests.MultiplicationRequestBody;
import cz.tomas.matikaapi.services.impl.MainTaskGenerator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@Tag(
        name = "Multiplication Math Assignment"
)
public class MultiplicationInputController {

    @Autowired
    public MultiplicationInputController(MainTaskGenerator mainTaskGenerator) {
        this.mainTaskGenerator = mainTaskGenerator;
    }

    MainTaskGenerator mainTaskGenerator;

    @PostMapping(
            value = "/post/nasobeni",
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
            @RequestBody @Valid MultiplicationRequestBody requestBody
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

}
