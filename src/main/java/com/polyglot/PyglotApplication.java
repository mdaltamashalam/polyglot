package com.polyglot;

import com.polyglot.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PyglotApplication {

	public static void main(String[] args) {
		SpringApplication.run(PyglotApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(courseService coursesService) {
		return args -> {
			// Add data to the database
			coursesService.addCourses();
		};
	}

}
