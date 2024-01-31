package cz.tomas.matikaapi.controller;

import cz.tomas.matikaapi.dto.MathOperationTypes;
import cz.tomas.matikaapi.dto.MathTaskInstructions;
import cz.tomas.matikaapi.dto.MathTasks;
import cz.tomas.matikaapi.services.AdditionGenerator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(
        name = "Math Assignment"
)
public class ApiInputController {

    @Autowired
    public ApiInputController(AdditionGenerator additionGenerator) {
        this.additionGenerator = additionGenerator;
    }

    AdditionGenerator additionGenerator;

    /**
     * Data input for providing addition tasks (math assignments)
     * @param maxVysledek is a number telling the application what should be the max result of adding two numbers
     * @param tasks controls how many assignments should be returned
     * @return instance of {@link MathTasks}, if used via API, this returns json
     */
    @GetMapping(
            value="/api/scitani/{max}",
            produces = "application/json"
    )
    @Operation(
            summary = "Returns addition math assignments where the result is less than 'max' value"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Tasks successfully provided")
            }
    )
    @ResponseStatus(HttpStatus.OK)
    public MathTasks scitani(
            @PathVariable("max") long maxVysledek,
            @RequestParam(
                    name = "tasks",
                    required = false,
                    defaultValue = "20"
            ) int tasks
    ){
        MathTaskInstructions mathTaskInstructions = MathTaskInstructions.builder()
                .mathOperationType(MathOperationTypes.ADDITION)
                .numberOfTasks(tasks)
                .maxLimit(maxVysledek)
                .build();
        return additionGenerator.buildTask(mathTaskInstructions);
    }

    @GetMapping(
            value = "/api/odcitani/{maxSubtractionFrom}/{maxValueToSubtract}",
            produces = "application/json"
    )
    @Operation(
            summary = "Returns subtraction math assignments"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Tasks successfully provided")
            }
    )
    public MathTasks odcitatni(
            @PathVariable("maxSubtractionFrom") long maxFrom,
            @PathVariable("maxValueToSubtract") long maxValueToSubtract,
            @RequestParam(
                    name = "tasks",
                    required = false,
                    defaultValue = "20"
            ) int tasks
    ){
        MathTaskInstructions instructions = MathTaskInstructions.builder()
                .mathOperationType(MathOperationTypes.SUBTRACTION)
                .maxFirstValue(maxFrom)
                .maxSecondValue(maxValueToSubtract)
                .numberOfTasks(tasks)
                .build();
        return additionGenerator.buildTask(instructions);
    }

    @GetMapping(
            value = "/api/nasobeni/{maxFirstValue}/{maxSecondValue}/{maxResult}",
            produces = "application/json"
    )
    @Operation(
            summary = "Returns multiplication math assignments where result is less than max result"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Tasks successfully provided")
            }
    )
    public MathTasks nasobeni(
            @PathVariable("maxFirstValue") long maxFirstValue,
            @PathVariable("maxSecondValue") long maxSecondValue,
            @PathVariable("maxResult") long maxResult,
            @RequestParam(
                    name = "tasks",
                    required = false,
                    defaultValue = "20"
            ) int tasks
    ) {
        MathTaskInstructions instructions = MathTaskInstructions.builder()
                .mathOperationType(MathOperationTypes.MULTIPLICATION)
                .maxFirstValue(maxFirstValue)
                .maxSecondValue(maxSecondValue)
                .maxLimit(maxResult)
                .numberOfTasks(tasks)
                .build();
        return additionGenerator.buildTask(instructions);
    }

}
