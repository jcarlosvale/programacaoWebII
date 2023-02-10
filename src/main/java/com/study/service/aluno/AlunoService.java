package com.study.service.aluno;


import com.study.dto.commons.TodoDto;
import com.study.dto.request.AlunoRequest;
import com.study.dto.response.AlunoResponse;
import com.study.dto.response.TutorResponse;

import java.util.List;

public interface AlunoService {

     AlunoResponse createAluno(AlunoRequest request);
     AlunoResponse getById(int id);
    List<AlunoResponse> getAll();
    TutorResponse updateTutor(int id, int idProfessor);
    List<AlunoResponse> getTutoradosByProfessorId(Integer id);
    public TodoDto generateRandomTodo();

}
