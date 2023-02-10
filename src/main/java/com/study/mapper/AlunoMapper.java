package com.study.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.study.dto.AlunoResponse;

import com.study.entity.AlunoEntity;


@Service
public class AlunoMapper {
    
    public List<AlunoResponse> toResponse(List<AlunoEntity> listOfEntities) {

        if (Objects.isNull(listOfEntities)) return new ArrayList<>();

        return listOfEntities.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public AlunoResponse toResponse(AlunoEntity entity) {

        Objects.requireNonNull(entity, "entity must not be null");

        var response =
                AlunoResponse.builder()
                    .id(entity.getId())
                    .nome(entity.getNome())
                    .email(entity.getEmail())
                    .cpf(entity.getCpf())
                    .matricula(entity.getMatricula())
                    .sexo(entity.getSexo())
                    .idade(entity.getIdade())
                    .build();
        if (Objects.nonNull(entity.getTutor())) {
            ((AlunoResponse) response).setTutor(entity.getTutor().getName());
        }

        return (AlunoResponse) response;
    }

   /*  public TutorResponse toTutorResponse(ProfessorEntity entity) {

        Objects.requireNonNull(entity, "entity must not be null");

        var formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");

        return TutorResponse.builder()
                .tutor(entity.getName())
                .atualizacao(formatter.format(LocalDateTime.now()))
                .build();

    } */

    public AlunoEntity toEntity(AlunoResponse aluno) {
        if (Objects.isNull(aluno)) {
            return null;
        } else {
            return AlunoEntity.builder()
                    .nome(aluno.getNome())
                    .email(aluno.getEmail())
                    .cpf(aluno.getCpf())
                    .matricula(aluno.getMatricula())
                    .sexo(aluno.getSexo())
                    .build();
        }
   }

}
