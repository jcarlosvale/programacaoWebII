package com.study.service;

import java.util.List;

import com.study.dto.request.AlunoRequest;
import com.study.dto.response.AlunoResponse;
import com.study.dto.response.TutorResponse;

public interface AlunoService {

	AlunoResponse createAluno(AlunoRequest request);

	AlunoResponse getById(int id);

	List<AlunoResponse> getAll();

	TutorResponse updateTutor(int id, int idProfessor);

	List<AlunoResponse> getTutoradosByProfessorId(Integer id);

}