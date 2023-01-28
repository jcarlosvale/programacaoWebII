package com.study.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.study.domain.dto.CursoDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CursoService {

	private final Map<Integer, CursoDTO> repository;
		
	public List<CursoDTO> getAll(){
		return new ArrayList<>(repository.values());
	}
	
	public CursoDTO getById(final int id) {
		return repository.get(id);
	}
	
	public CursoDTO save(final CursoDTO curso) {
		repository.put(curso.getId(), curso);
		return curso;
	}
	
	public CursoDTO update(final int id, final CursoDTO curso) {
		repository.put(id, curso);
		return curso;
	}
	
	public void delete(final int id) {
		repository.remove(id);
	}
	
	
}

