package com.daniel.taskmanagerbackend;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Task management application Rest APIs",
				description = "Documentation for the task management application service",
				contact = @Contact(
						name = "Fredy Daniel",
						email = "fredyd900@gmail.com"
				),
				version = "v1.0"
		)
)
public class TaskManagerBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagerBackendApplication.class, args);
	}

}
