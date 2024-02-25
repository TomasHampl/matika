package cz.tomas.matikaapi.services.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberGeneratorTest {

    NumberGenerator underTest;

    @BeforeEach
    void setup(){
        underTest = new NumberGenerator();
    }

    @Test
    void getRandomLong() {
        long simpleLong = underTest.getRandomLong(1,100);
        assertTrue(simpleLong > 1);
        assertTrue(simpleLong <= 100);
    }

    @Test
    void getRandomLongWithError(){
        long badLong = underTest.getRandomLong(50, 30);
        assertEquals(0, badLong);
    }
}