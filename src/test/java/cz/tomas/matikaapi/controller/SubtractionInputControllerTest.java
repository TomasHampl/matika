package cz.tomas.matikaapi.controller;

import cz.tomas.matikaapi.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SubtractionInputControllerTest {

    TestUtil testUtil = new TestUtil();

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @Test
    void endpointNotFound() throws Exception {
        mockMvc.perform(post("/post/nonsense"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void endpointFoundNoDataProvided() throws Exception {
        mockMvc.perform(post("/post/odcitani")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("nonsense"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void positiveMessage() throws Exception {
        String fileContents = testUtil.getFileAsString("src/test/resources/subtraction-post-body.json");
        mockMvc.perform(post("/post/odcitani")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(fileContents))
                .andExpect(status().is2xxSuccessful());
    }
}