package com.study;

import com.study.domain.dto.DisciplinasResponse;
import com.study.domain.model.Alunos;
import com.study.domain.model.Disciplinas;
import com.study.domain.model.Professores;
import com.study.domain.repositories.AlunosRepository;
import com.study.domain.repositories.DisciplinaRepository;
import com.study.domain.repositories.ProfessoresRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;

@SpringBootApplication
@RequiredArgsConstructor
public class SchoolApiApplication implements CommandLineRunner {


	private final ProfessoresRepository professoresRepository;
	private final AlunosRepository alunosRepository;
	private final DisciplinaRepository disciplinaRepository;


	public static void main(String[] args) {
		SpringApplication.run(SchoolApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var professor1 =
				Professores.builder()
						.nome("Jose")
						.titulo("Professor de Spring")
						.sexo("Masculino")
						.build();
		var professor2 =
				Professores.builder()
						.nome("Maria")
						.titulo("Professora de Artes")
						.sexo("Masculino")
						.build();

		professoresRepository.save(professor1);
		professoresRepository.save(professor2);


		var aluno1 =
				Alunos.builder()
						.nome("AlunoX")
						.build();
		var aluno2 =
				Alunos.builder()
						.nome("AlunoY")
						.build();

		alunosRepository.save(aluno1);
		alunosRepository.save(aluno2);

		var disciplina1 =
				Disciplinas.builder()
						.nome("Spring Boot")
						.descricao("Materia sobre Spring Boot")
						.duracao("80h")
						.titular(professor1)
						.build();

		var disciplina2 =
				Disciplinas.builder()
						.nome("Artes")
						.descricao("Materia sobre Artes")
						.duracao("80h")
						.titular(professor2)
						.build();

		disciplinaRepository.save(disciplina1);
		disciplinaRepository.save(disciplina2);

	}
}
