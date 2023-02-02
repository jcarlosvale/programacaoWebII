package com.study;

import lombok.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;

@SpringBootApplication
@RequiredArgsConstructor
public class SchoolApiApplication implements CommandLineRunner{



	public static void main(String[] args) {
		SpringApplication.run(SchoolApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
