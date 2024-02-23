package cz.tomas.matikaapi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AdditionInputControllerTest {


    @Autowired
    MockMvc mockMvc;

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

        Path path = Paths.get("src/test/resources/addition-post-body.json");
        StringBuilder builder = new StringBuilder();
        try (Stream<String> stream = Files.lines(path)){
            stream.forEach(builder::append);
        }
        String fileContents = builder.toString();

        mockMvc.perform(post("/post/scitani")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(fileContents))
                .andExpect(status().is2xxSuccessful());
    }

}