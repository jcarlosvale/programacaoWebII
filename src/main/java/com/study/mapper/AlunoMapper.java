package com.study.mapper;

import com.study.domain.dto.AlunoRequestDto;
import com.study.domain.dto.AlunoResponseDto;
import com.study.domain.dto.TutorResponse;
import com.study.model.AlunoModel;
import com.study.model.ProfessorModel;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AlunoMapper {
    public List<AlunoResponseDto> toResponse(List<AlunoModel> listOfAlunos) {

        if (Objects.isNull(listOfAlunos)) return new ArrayList<>();

        return listOfAlunos.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public AlunoResponseDto toResponse(AlunoModel entity) {

        if (Objects.isNull(entity)) return null;

        var response =  AlunoResponseDto.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .matricula(entity.getMatricula())
                .sexo(entity.getSexo())
                .build();

        if (Objects.nonNull(entity.getTutor())) {
            response.setTutor(entity.getTutor().getNome());
        }

        return response;
    }

    public AlunoModel toEntity(AlunoRequestDto request) {
        if (Objects.isNull(request)) {
            return null;
        } else {
            return AlunoModel.builder()
                    .nome(request.getNome())
                    .matricula(request.getMatricula())
                    .sexo(request.getSexo())
                    .build();
        }
    }

    public TutorResponse toResponse(ProfessorModel entity) {

        Objects.requireNonNull(entity, "entity must not be null");

        var formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");

        return TutorResponse.builder()
                .tutor(entity.getNome())
                .atualizacao(formatter.format(LocalDateTime.now()))
                .build();

    }
}
