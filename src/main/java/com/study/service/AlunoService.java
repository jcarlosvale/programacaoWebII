package com.study.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.study.domain.dto.response.AlunoResponseDTO;
import com.study.mappers.AlunoMapper;
import com.study.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import com.study.domain.dto.AlunoDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AlunoService {

	private final AlunoRepository repository;
	private final AlunoMapper mapper;
		
	public List<AlunoResponseDTO> getAll(){
		return mapper.toResponseDTO(repository.findAll()) ;
	}
	
	public AlunoResponseDTO getById(final int id) {
		return mapper.toResponseDTO(repository.findById(id));
	}
	
	public AlunoDTO save(final AlunoDTO aluno) {
		repository.save(mapper.fromResponseDTO(aluno));
		return aluno;
	}
	
	public AlunoDTO update(final int id, final AlunoDTO aluno) {
		repository.put(id, aluno);
		return aluno;
	}
	
	public void delete(final int id) throws Exception {
		if(repository.containsKey(id)) {
			repository.remove(id);
		} else {
			throw new Exception("Id não encontrado");
		}
	}
	
	
}

