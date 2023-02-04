package com.study.mapper;

import com.study.dto.AlunoRequestDto;
import com.study.dto.AlunoResponseDto;
import com.study.model.Aluno;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AlunoMapper {
    public List<AlunoResponseDto> toResponse(List<Aluno> listaAluno) {

        if (Objects.isNull(listaAluno)) return new ArrayList<>();

        return listaAluno.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public AlunoResponseDto toResponse(Aluno entity) {

        if (Objects.isNull(entity)) return null;

        return  AlunoResponseDto.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .matricula(entity.getMatricula())
                .genero(entity.getGenero())
                .build();
    }

    public Aluno toEntity(AlunoRequestDto request) {
        if (Objects.isNull(request)) {
            return null;
        } else {
            return Aluno.builder()
                    .nome(request.getNome())
                    .matricula(request.getMatricula())
                    .genero(request.getGenero())
                    .build();
        }
    }
}
