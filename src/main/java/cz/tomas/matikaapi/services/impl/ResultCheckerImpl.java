package cz.tomas.matikaapi.services.impl;

import cz.tomas.matikaapi.dto.MathOperationTypes;
import cz.tomas.matikaapi.dto.ResultAssessment;
import cz.tomas.matikaapi.dto.VerificationResponse;
import cz.tomas.matikaapi.dto.requests.VerificationRequestBody;
import cz.tomas.matikaapi.services.ResultChecker;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResultCheckerImpl implements ResultChecker {
    @Override
    public VerificationResponse validateResult(VerificationRequestBody requestBody) {

        MathOperationTypes types = requestBody.getOperationTypes();
        ResultAssessment resultAssessment = verifyResult(requestBody.getFirstNumber(), requestBody.getSecondNumber(), types, requestBody.getResult());

        return VerificationResponse.builder()
                .correct(resultAssessment.isCorrect())
                .requestBody(requestBody)
                .correctResult(resultAssessment.getCorrectResult())
                .build();
    }

    @Override
    public List<VerificationResponse> validateResults(List<VerificationRequestBody> requestBodies) {
        return List.of();
    }

    private ResultAssessment verifyResult(long firstNumber, long secondNumber, MathOperationTypes operation, long result){
        ResultAssessment.ResultAssessmentBuilder builder = ResultAssessment.builder();
        switch (operation){
            case DIVISION -> {
                builder.correct(firstNumber / secondNumber == result);
                builder.correctResult(firstNumber / secondNumber);
            }
            case SUBTRACTION -> {
                builder.correct(firstNumber - secondNumber == result);
                builder.correctResult(firstNumber - secondNumber);
            }
            case MULTIPLICATION -> {
                builder.correct(firstNumber * secondNumber == result);
                builder.correctResult(firstNumber * secondNumber);
            }
            default -> {
                builder.correct(firstNumber + secondNumber == result);
                builder.correctResult(firstNumber + secondNumber);
            }
        }
        return builder.build();
    }
}
