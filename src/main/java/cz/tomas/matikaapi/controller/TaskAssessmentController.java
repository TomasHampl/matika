package cz.tomas.matikaapi.controller;

import cz.tomas.matikaapi.dto.VerificationResponse;
import cz.tomas.matikaapi.dto.requests.VerificationRequestBody;
import cz.tomas.matikaapi.services.ResultChecker;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Checks math tasks")
public class TaskAssessmentController {

    private final ResultChecker resultChecker;

    public TaskAssessmentController(ResultChecker resultChecker) {
        this.resultChecker = resultChecker;
    }

    @PostMapping(value = "/task/check",produces = MediaType.APPLICATION_JSON_VALUE)
    public VerificationResponse validateResult(@RequestBody @Valid VerificationRequestBody verificationRequestBody) {
        return resultChecker.validateResult(verificationRequestBody);
    }



}
