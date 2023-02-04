package com.study.dto.mapper;

import com.study.dto.response.AlunoResponse;
import com.study.dto.response.TutorResponse;
import com.study.entity.Aluno;
import com.study.entity.Professor;
import org.springframework.stereotype.Service;

import javax.swing.text.DateFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AlunoMapper {

    public List<AlunoResponse> toResponse(List<Aluno> listOfEntities) {

        if (Objects.isNull(listOfEntities)) return new ArrayList<>();

        return listOfEntities.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());

    }

    public AlunoResponse toResponse(Aluno entity) {

        Objects.requireNonNull(entity, "A entidade não pode ser null");

        AlunoResponse response = AlunoResponse.builder().build();
        response.setId(entity.getId());
        response.setName(entity.getName());

        if (Objects.nonNull(entity.getTutor())) {
            response.setTutor(entity.getTutor().getName());
        }

        return response;
    }

    public TutorResponse toResponse(Professor entity) {

        Objects.requireNonNull(entity, "A entidade não pode ser null");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        return TutorResponse.builder()
                .tutor(entity.getName())
                .atualizacao(formatter.format(LocalDateTime.now()))
                .build();
    }

}
