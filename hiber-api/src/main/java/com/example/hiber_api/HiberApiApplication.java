package com.example.hiber_api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.hiber_api.repository.EmployeeRepository;

@SpringBootApplication
public class HiberApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HiberApiApplication.class, args);
	}

    @Bean
    CommandLineRunner init(EmployeeRepository employeeRepository) {
        return args -> {
            employeeRepository.createEmployees();
        };
    }

}
