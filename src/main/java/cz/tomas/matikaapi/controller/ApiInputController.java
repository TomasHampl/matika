package cz.tomas.matikaapi.controller;

import cz.tomas.matikaapi.dto.MathOperationTypes;
import cz.tomas.matikaapi.dto.MathTasks;
import cz.tomas.matikaapi.services.AdditionGenerator;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
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
            summary = "Returns a number of 'addition' math assignments where the result equals the 'max' value"
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
        return additionGenerator.buildTasks(MathOperationTypes.ADDITION,tasks,maxVysledek);
    }

    @GetMapping(
            value = "/api/odcitani/{maxSubtractionFrom}/{maxValueToSubtract}",
            produces = "application/json"
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
        return additionGenerator.buildTasks(MathOperationTypes.ADDITION, tasks,maxFrom);
    }

}
