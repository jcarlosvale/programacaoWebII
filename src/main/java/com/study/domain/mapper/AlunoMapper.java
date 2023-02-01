package com.study.domain.mapper;

import com.study.domain.dto.AlunosRequest;
import com.study.domain.dto.AlunosResponse;
import com.study.domain.model.Alunos;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AlunoMapper {
    public List<AlunosResponse> toResponse(List<Alunos> listOfAlunos) {

        if (Objects.isNull(listOfAlunos)) return new ArrayList<>();

        return listOfAlunos.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public AlunosResponse toResponse(Alunos entity) {

        if (Objects.isNull(entity)) return null;

        return AlunosResponse.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .matricula(entity.getMatricula())
                .sexo(entity.getSexo())
                .build();
    }

    public Alunos toEntity(AlunosRequest request) {
        if (Objects.isNull(request)) {
            return null;
        } else {
            return Alunos.builder()
                    .nome(request.getNome())
                    .matricula(request.getMatricula())
                    .sexo(request.getSexo())
                    .build();
        }
    }
}
