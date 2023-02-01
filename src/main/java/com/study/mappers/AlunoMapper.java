package com.study.mappers;

import com.study.domain.dto.AlunoDTO;
import com.study.domain.dto.request.AlunoRequestDTO;
import com.study.domain.dto.response.AlunoResponseDTO;
import com.study.entity.Aluno;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AlunoMapper {

    public AlunoResponseDTO toResponseDTO(Optional<Aluno> byId) { //TODO CORRIGIR
        if (byId == null){
            return null;
        }

        return AlunoResponseDTO.builder()
                .id(aluno.getId())
                .nome(aluno.getNome())
                .matricula(aluno.getMatricula())
                .sexo(aluno.getSexo())
                .build();
    }

    public Aluno fromResponseDTO(AlunoResponseDTO alunoResponseDTO){
        if (alunoResponseDTO == null){
            return null;
        }

        return Aluno.builder()
                .id(alunoResponseDTO.getId())
                .nome(alunoResponseDTO.getNome())
                .matricula(alunoResponseDTO.getMatricula())
                .sexo(alunoResponseDTO.getSexo())
                .build();
    }

    public List<AlunoResponseDTO> toResponseDTO(List<Aluno> listaAlunos) {
        return listaAlunos.stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    public AlunoResponseDTO toResponseDTO(Optional<Aluno> byId) {

    }
}
