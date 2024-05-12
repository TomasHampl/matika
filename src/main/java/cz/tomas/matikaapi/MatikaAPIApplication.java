package cz.tomas.matikaapi;

import io.micrometer.core.instrument.binder.jvm.JvmThreadMetrics;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MatikaAPIApplication {

	@Bean
	JvmThreadMetrics threadMetrics() {
		return new JvmThreadMetrics();
	}

	public static void main(String[] args) {
		SpringApplication.run(MatikaAPIApplication.class, args);
	}

}
