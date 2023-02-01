package com.study.mappers;

import com.study.domain.dto.request.ProfessorRequestDTO;
import com.study.domain.dto.response.ProfessorResponseDTO;
import com.study.entity.Professor;
import org.springframework.stereotype.Component;

@Component
public class ProfessorMapper {

    public ProfessorRequestDTO toRequestDTO (Professor professor){
        if (professor == null){
            return null;
        }

        return ProfessorRequestDTO.builder()
                .nome(professor.getNome())
                .sexo(professor.getSexo())
                .titulo(professor.getTitulo())
                .build();
    }

    public Professor fromResponseDTO(ProfessorResponseDTO professorResponseDTO){
        if (professorResponseDTO == null){
            return null;
        }

        return Professor.builder()
                .id(professorResponseDTO.getId())
                .nome(professorResponseDTO.getNome())
                .sexo(professorResponseDTO.getSexo())
                .titulo(professorResponseDTO.getTitulo())
                .build();
    }
}
