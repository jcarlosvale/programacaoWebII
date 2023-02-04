package com.study.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.study.domain.dto.DisciplinaDTO;
import com.study.mappers.CursoMapper;
import com.study.repository.DisciplinaRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DisciplinaService {
private final DisciplinaRepository disciplinaRepository;
private final CursoMapper mapper;
	
		
	public List<DisciplinaDTO> getAll(){
	}
	
	public DisciplinaDTO getById(final int id) {
		return repository.get(id);
	}
	
	public DisciplinaDTO save(final DisciplinaDTO curso) {
		repository.put(curso.getId(), curso);
		return curso;
	}
	
	public DisciplinaDTO update(final int id, final DisciplinaDTO curso) {
		repository.put(id, curso);
		return curso;
	}
	
	public void delete(final int id) throws Exception {
		if(repository.containsKey(id)) {
			repository.remove(id);
		} else {
			throw new Exception("Id não encontrado");
		}
	}
	
	
}

