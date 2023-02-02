package com.study.service;

import java.util.List;
import com.study.domain.dto.response.AlunoResponseDTO;
import com.study.mappers.AlunoMapper;
import com.study.repository.AlunoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Slf4j
public class AlunoService {

	private final AlunoRepository repository;
	private final AlunoMapper mapper;

	public List<AlunoResponseDTO> getAll() {
		return mapper.toResponseDTO(repository.findAll());
	}

	public AlunoResponseDTO getById(final int id) {
		log.info("Retornando aluno por id{}", id);

		var optionalAluno = repository.findById(id);

		if (optionalAluno.isPresent()) {
			return mapper.toResponseDTO(optionalAluno.get());
		}
		return new AlunoResponseDTO();
	}

	public AlunoResponseDTO save(final AlunoResponseDTO aluno) {
		repository.save(mapper.fromResponseDTO(aluno));
		return aluno;
	}

	public AlunoResponseDTO update(final AlunoResponseDTO aluno) {
		repository.save(mapper.fromResponseDTO(aluno));
		return aluno;
	}

	public void delete(final int id) throws Exception {
		log.info("Deletando aluno id - {}", id);
		repository.deleteById(id);
	}

}
