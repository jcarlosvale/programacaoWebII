package com.study;

import com.study.model.*;
import com.study.repository.*;
import lombok.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;

import javax.transaction.*;

@SpringBootApplication
@RequiredArgsConstructor
public class SchoolApiApplication implements CommandLineRunner{
	private final StudentRepository studentRepository;
	private final TeacherRepository teacherRepository;
	private final SubjectRepository subjectRepository;

	public static void main(String[] args) {
		SpringApplication.run(SchoolApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var professor1 =
				Teacher.builder()
						.name("Jose")
						.email("jose@test.com")
						.cpf("050.418.820-88")
						.build();
		var professor2 =
				Teacher.builder()
						.name("Maia")
						.email("maria@test.com")
						.cpf("744.388.770-16")
						.build();

		teacherRepository.save(professor1);
		teacherRepository.save(professor2);


		var aluno1 =
				Student.builder()
						.name("AlunoX")
						.build();
		var aluno2 =
				Student.builder()
						.name("AlunoY")
						.build();

		studentRepository.save(aluno1);
		studentRepository.save(aluno2);

		var disciplina1 =
				Subject.builder()
						.name("Java")
						.build();

		var disciplina2 =
				Subject.builder()
						.name("Python")
						.build();

		subjectRepository.save(disciplina1);
		subjectRepository.save(disciplina2);
	}
}
