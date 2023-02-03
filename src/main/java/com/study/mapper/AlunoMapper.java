package com.study.mapper;

import com.study.dto.AlunoRequestDto;
import com.study.dto.AlunoResponseDto;
import com.study.model.Aluno;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AlunoMapper {

    public List<AlunoResponseDto> toResponse(List<Aluno> listOfAlunos){
        if (Objects.isNull(listOfAlunos)) return new ArrayList<>();
        listOfAlunos
        // TODO: IMPLEMENTAR MAPPER
        return new ArrayList<>();
    }

    public AlunoResponseDto toResponse(Aluno aluno){
        if(Objects.isNull(aluno)) return null;

        return AlunoResponseDto.build()
        // TODO: IMPLEMENTAR MAPPER
        return new AlunoResponseDto();
    }

    public Aluno toEntity(AlunoRequestDto alunoRequest) {
        // TODO: IMPLEMENTAR MAPPER
        return new Aluno();
    }
}
