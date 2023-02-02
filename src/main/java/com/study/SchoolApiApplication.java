package com.study;

import com.study.model.*;
import com.study.repository.*;
import lombok.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;

import javax.transaction.*;

@SpringBootApplication
@RequiredArgsConstructor
public class SchoolApiApplication implements CommandLineRunner{

	@Autowired
	AlunoRepository alunoRepository;

	@Autowired
	ProfessorRepository professorRepository;

	public static void main(String[] args) {
		SpringApplication.run(SchoolApiApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {

		var professor = professorRepository.save(Professor.builder().name("Jose").build());

		//var professor  =Professor.builder().name("Jose").build();

		alunoRepository.save(Aluno.builder().name("Joao").tutor(professor).build());

//		System.out.println("******Teste: ");
//
//		var aluno = alunoRepository.findById(1).get();
//
//		//for (int i = 0; i < alunos.size(); i++) {
//			System.out.println(aluno.getName());
//			System.out.println("####################");
//			System.out.println(aluno.getTutor());
//		//}



	}
}
