package com.study;

import com.study.model.*;
import com.study.repository.*;
import lombok.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;

@SpringBootApplication
@RequiredArgsConstructor
public class SchoolApiApplication implements CommandLineRunner{

	private final AlunoRepository alunoRepository;
	private final ProfessorRepository professorRepository;
	private final DisciplinaRepository disciplinaRepository;

	public static void main(String[] args) {
		SpringApplication.run(SchoolApiApplication.class, args);
	}

	@Override
	public void run(String... args) {
		var professor1 =
				Professor.builder()
						.name("Jose")
						.email("jose@test.com")
						.cpf("050.418.820-88")
						.build();
		var professor2 =
				Professor.builder()
						.name("Maia")
						.email("maria@test.com")
						.cpf("744.388.770-16")
						.build();

		professorRepository.save(professor1);
		professorRepository.save(professor2);


		var aluno1 =
				Aluno.builder()
						.name("AlunoX")
						.build();
		var aluno2 =
				Aluno.builder()
						.name("AlunoY")
						.build();

		alunoRepository.save(aluno1);
		alunoRepository.save(aluno2);

		var disciplina1 =
				Disciplina.builder()
						.name("Java")
						.build();

		var disciplina2 =
				Disciplina.builder()
						.name("Python")
						.build();

		disciplinaRepository.save(disciplina1);
		disciplinaRepository.save(disciplina2);

	}
}
