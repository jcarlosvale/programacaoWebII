package com.study.domain.mapper;

import com.study.domain.dto.DisciplinasRequest;
import com.study.domain.dto.DisciplinasResponse;
import com.study.domain.dto.ProfessoresResponse;
import com.study.domain.model.Disciplinas;
import com.study.domain.model.Professores;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DisciplinaMapper {
    public List<DisciplinasResponse> toResponse(List<Disciplinas> listOfCursos) {

        if (Objects.isNull(listOfCursos)) return new ArrayList<>();

        return listOfCursos.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public DisciplinasResponse toResponse(Disciplinas entity) {

        if (Objects.isNull(entity)) return null;

        var response = DisciplinasResponse.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .descricao(entity.getDescricao())
                .duracao(entity.getDuracao())
                .titular(entity.getTitular().getNome())
                .build();

        if (Objects.nonNull(entity.getTitular())) {
            response.setTitular(entity.getTitular().getNome());
        }

        return response;
    }

    public Disciplinas toEntity(DisciplinasRequest request) {
        if (Objects.isNull(request)) {
            return null;
        } else {
            return Disciplinas.builder()
                    .nome(request.getNome())
                    .descricao(request.getDescricao())
                    .duracao(request.getDuracao())
                    .build();
        }
    }

    public ProfessoresResponse toResponse(Professores entity) {

        if (Objects.isNull(entity)) return null;

        return ProfessoresResponse.builder()
                .nome(entity.getNome())
                .titulo(entity.getTitulo())
                .sexo(entity.getSexo())
                .disciplinas(entity.getDisciplinas().getNome())
                .build();

    }
}
