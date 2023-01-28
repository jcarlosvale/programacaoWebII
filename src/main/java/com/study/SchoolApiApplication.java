package com.study;

import lombok.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;

@SpringBootApplication
@RequiredArgsConstructor
public class SchoolApiApplication implements CommandLineRunner{

	private final Owner owner;
	private final Employee employee;

	public static void main(String[] args) {
		SpringApplication.run(SchoolApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Owner " + owner.getName());
		System.out.println("Employee " + employee.getName());

	}
}
