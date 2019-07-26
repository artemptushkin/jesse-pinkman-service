package ru.alfabank.contracts.jessepinkman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@SpringBootApplication
public class JessePinkmanApplication {

	public static void main(String[] args) {
		SpringApplication.run(JessePinkmanApplication.class, args);
	}

	@Bean
	RestTemplate heisenbergRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory("http://localhost:8080"));
		return restTemplate;
	}
}
