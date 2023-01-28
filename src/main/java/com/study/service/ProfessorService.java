package com.study.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.study.domain.dto.ProfessorDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProfessorService {

	private final Map<Integer, ProfessorDTO> repository;
		
	public List<ProfessorDTO> getAll(){
		return new ArrayList<>(repository.values());
	}
	
	public ProfessorDTO getById(final int id) {
		return repository.get(id);
	}
	
	public ProfessorDTO save(final ProfessorDTO professor) {
		repository.put(professor.getId(), professor);
		return professor;
	}
	
	public ProfessorDTO update(final int id, final ProfessorDTO professor) {
		repository.put(id, professor);
		return professor;
	}
	
	public void delete(final int id) throws Exception {
		if(repository.containsKey(id)) {
			repository.remove(id);
		} else {
			throw new Exception("Id não encontrado");
		}
	}
	
}

