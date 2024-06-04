package cz.tomas.matikaapi.controller;

import cz.tomas.matikaapi.TestUtil;
import cz.tomas.matikaapi.configuration.AppConfiguration;
import cz.tomas.matikaapi.dto.constants.MatikaAPIConstants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
 class ControllerTests {

    TestUtil testUtil = new TestUtil();

    @Autowired
    MockMvc mockMvc;

    @Autowired
    AppConfiguration appConfiguration;

    @Test
    void additionApiEndpointNotFound() throws Exception {
        mockMvc.perform(post("/post/nonsense"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void additionGetNotFound() throws Exception {
        mockMvc.perform(get("/post/scitani"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void additionPostRequest() throws Exception {
        String fileContents = testUtil.getFileAsString("src/test/resources/addition-post-body.json");
        mockMvc.perform(post(MatikaAPIConstants.ADDITION_POST_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(fileContents))
                .andExpect(status().is2xxSuccessful());
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
        mockMvc.perform(post(MatikaAPIConstants.DIVISION_POST_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(fileContents))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void endpointNotFound() throws Exception {
        mockMvc.perform(post("/post/nonsense"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void endpointFoundNoDataProvided() throws Exception {
        mockMvc.perform(post(MatikaAPIConstants.SUBTRACTION_POST_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("nonsense"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void positiveMessage() throws Exception {
        String fileContents = testUtil.getFileAsString("src/test/resources/subtraction-post-body.json");
        mockMvc.perform(post(MatikaAPIConstants.SUBTRACTION_POST_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(fileContents))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void testMathTaskTypes() throws Exception {
        mockMvc.perform(get(MatikaAPIConstants.TASKS_TYPES_PATH))
                .andExpect(status().is2xxSuccessful())
                .andExpect(header().exists("content-type"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }
}
