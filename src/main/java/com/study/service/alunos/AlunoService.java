package com.study.service.alunos;


import com.study.domain.dto.AlunoDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AlunoService {

     ResponseEntity<Void> createAluno(AlunoDto aluno);
     ResponseEntity<AlunoDto> getById(int id);
    ResponseEntity<List<AlunoDto>> getAll();
    ResponseEntity<AlunoDto> update(int id, AlunoDto aluno);
    ResponseEntity<Void> delete(int id);
    ResponseEntity<List<AlunoDto>> getByPrefix(String prefixo);

}
