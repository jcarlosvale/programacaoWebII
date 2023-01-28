package com.study.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.study.domain.dto.AlunoDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AlunoService {

	private final Map<Integer, AlunoDTO> repository;
		
	public List<AlunoDTO> getAll(){
		return new ArrayList<>(repository.values());
	}
	
	public AlunoDTO getById(final int id) {
		return repository.get(id);
	}
	
	public AlunoDTO save(final AlunoDTO aluno) {
		repository.put(aluno.getId(), aluno);
		return aluno;
	}
	
	public AlunoDTO update(final int id, final AlunoDTO aluno) {
		repository.put(id, aluno);
		return aluno;
	}
	
	public void delete(final int id) {
		repository.remove(id);
	}
	
	
}

