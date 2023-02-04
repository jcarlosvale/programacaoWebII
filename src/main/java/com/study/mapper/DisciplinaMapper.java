package com.study.mapper;


import com.study.dto.DisciplinaResponse;
import com.study.model.Disciplina;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DisciplinaMapper {

    private final ProfessorMapper professorMapper;
    public DisciplinaResponse toResponse(Disciplina disciplina) {
        if (disciplina == null) return new DisciplinaResponse();

        return DisciplinaResponse.builder()
                .id(disciplina.getId())
                .nome(disciplina.getNome())
                .descricao(disciplina.getDescricao())
                .duracao(disciplina.getDuracao())
                .titular(professorMapper.toResponse(disciplina.getTitular()))
                .build();
    }
}
