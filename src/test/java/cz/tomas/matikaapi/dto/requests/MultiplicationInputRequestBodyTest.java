package cz.tomas.matikaapi.dto.requests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MultiplicationInputRequestBodyTest {

    InputRequestBody underTest;

    @BeforeEach
    void setUp() {
        underTest = InputRequestBody.builder().build();
    }

    @Test
    void testMaxResultDefaultValue(){
        long maxResult = underTest.getMaxResult();
        assertEquals(100, maxResult);
    }

    @Test
    void testMaxResult(){
        underTest.setMaxResult(30);
        long maxResult = underTest.getMaxResult();
        assertEquals(30, maxResult);
    }
}