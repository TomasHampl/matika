package cz.tomas.matikaapi.controller;

import cz.tomas.matikaapi.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DivisionInputControllerTest {

    TestUtil testUtil = new TestUtil();

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("Undefined HTTP method")
    void nonsenseTest() throws Exception {
        mockMvc.perform(get("/post/scitani"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("Undefined path")
    void undefinedPath() throws Exception {
        mockMvc.perform(post("/post/delenisss"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("Valid division scenario")
    void validDivisionScenario() throws Exception {
        String fileContents = testUtil.getFileAsString("src/test/resources/division-test-body.json");
        mockMvc.perform(post("/post/deleni")
                .contentType(MediaType.APPLICATION_JSON)
                .content(fileContents))
                .andExpect(status().is2xxSuccessful());
    }
}