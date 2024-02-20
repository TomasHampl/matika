package cz.tomas.matikaapi;

import cz.tomas.matikaapi.controller.AdditionInputController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MatikaAPIApplicationTests {

	@Autowired
	AdditionInputController additionInputController;

	@Test
	void contextLoads() {
		assertThat(additionInputController).isNotNull();
	}

}
