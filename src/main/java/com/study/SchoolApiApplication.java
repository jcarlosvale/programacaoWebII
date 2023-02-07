package com.study;

import com.study.domain.model.Disciplinas;
import com.study.domain.model.Professores;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;

@SpringBootApplication
public class SchoolApiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SchoolApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
