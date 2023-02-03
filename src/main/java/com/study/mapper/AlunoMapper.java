package com.study.mapper;

import com.study.dto.v3.*;
import com.study.model.*;
import org.springframework.stereotype.*;

import java.time.*;
import java.time.format.*;
import java.util.*;
import java.util.stream.*;

@Service
public class AlunoMapper {

    public List<AlunoResponse> toResponse(List<Aluno> listOfEntities) {

        if (Objects.isNull(listOfEntities)) return new ArrayList<>();

        return listOfEntities.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());

    }

    public AlunoResponse toResponse(Aluno entity) {

        Objects.requireNonNull(entity, "entity must not be null");

        var response =
                AlunoResponse.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .build();

        if (Objects.nonNull(entity.getTutor())) {
            response.setTutor(entity.getTutor().getName());
        }

        return response;
    }

    public TutorResponse toResponse(Professor entity) {

        Objects.requireNonNull(entity, "entity must not be null");

        var formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");

        return TutorResponse.builder()
                .tutor(entity.getName())
                .atualizacao(formatter.format(LocalDateTime.now()))
                .build();

    }
}
