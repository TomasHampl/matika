package cz.tomas.matikaapi.controller;

import cz.tomas.matikaapi.dto.MathOperationTypes;
import cz.tomas.matikaapi.dto.MathTaskInstructions;
import cz.tomas.matikaapi.dto.MathTasks;
import cz.tomas.matikaapi.dto.requests.SubtractionRequestBody;
import cz.tomas.matikaapi.services.impl.MainTaskGenerator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(
        name = "Subtraction Math Assignment"
)
public class SubtractionInputController {

    @Autowired
    public SubtractionInputController(MainTaskGenerator mainTaskGenerator) {
        this.mainTaskGenerator = mainTaskGenerator;
    }

    MainTaskGenerator mainTaskGenerator;

    @PostMapping(
            value = "/post/odcitani",
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
            @RequestBody @Valid SubtractionRequestBody requestBody
    ) {
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
