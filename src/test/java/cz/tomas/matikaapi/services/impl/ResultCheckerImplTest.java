package cz.tomas.matikaapi.services.impl;

import cz.tomas.matikaapi.dto.MathOperationTypes;
import cz.tomas.matikaapi.dto.VerificationResponse;
import cz.tomas.matikaapi.dto.requests.VerificationRequestBody;
import cz.tomas.matikaapi.services.ResultChecker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class ResultCheckerImplTest {

    ResultChecker underTest;

    @BeforeEach
    void setUp() {
        underTest = new ResultCheckerImpl();
    }

    @DisplayName("Addition")
    @ParameterizedTest
    @ValueSource(ints = {20,30})
    void resultValidationAddition(int result){
        VerificationRequestBody verificationRequestBody = VerificationRequestBody.builder()
                .result(result)
                .firstNumber(10)
                .secondNumber(20)
                .operationTypes(MathOperationTypes.ADDITION)
                .build();
        VerificationResponse verificationResponse = underTest.validateResult(verificationRequestBody);
        if(result == 20){
            assertFalse(verificationResponse.isCorrect());
        } else {
            assertTrue(verificationResponse.isCorrect());
        }
    }

    @DisplayName("Division")
    @ParameterizedTest
    @ValueSource(ints = {2,1})
    void resultValidationDivision(int result){
        VerificationRequestBody verificationRequestBody = VerificationRequestBody.builder()
                .result(result)
                .firstNumber(40)
                .secondNumber(20)
                .operationTypes(MathOperationTypes.DIVISION)
                .build();
        VerificationResponse verificationResponse = underTest.validateResult(verificationRequestBody);
        if(result == 1){
            assertFalse(verificationResponse.isCorrect());
        } else {
            assertTrue(verificationResponse.isCorrect());
        }
    }

    @DisplayName("Multiplication")
    @ParameterizedTest
    @ValueSource(ints = {20,25})
    void resultValidationMultiplication(int result){
        VerificationRequestBody verificationRequestBody = VerificationRequestBody.builder()
                .result(result)
                .firstNumber(4)
                .secondNumber(5)
                .operationTypes(MathOperationTypes.MULTIPLICATION)
                .build();
        VerificationResponse verificationResponse = underTest.validateResult(verificationRequestBody);
        if(result == 25){
            assertFalse(verificationResponse.isCorrect());
        } else {
            assertTrue(verificationResponse.isCorrect());
        }
    }

    @DisplayName("Subtraction")
    @ParameterizedTest
    @ValueSource(ints = {5,6})
    void resultValidationSubtraction(int result){
        VerificationRequestBody verificationRequestBody = VerificationRequestBody.builder()
                .result(result)
                .firstNumber(10)
                .secondNumber(5)
                .operationTypes(MathOperationTypes.SUBTRACTION)
                .build();
        VerificationResponse verificationResponse = underTest.validateResult(verificationRequestBody);
        if(result == 6){
            assertFalse(verificationResponse.isCorrect());
        } else {
            assertTrue(verificationResponse.isCorrect());
        }
    }
}