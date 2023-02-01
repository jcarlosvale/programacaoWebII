package com.study.mappers;

import com.study.domain.dto.request.CursoRequestDTO;
import com.study.domain.dto.response.CursoResponseDTO;
import com.study.entity.Curso;
import org.springframework.stereotype.Component;

@Component
public class CursoMapper {
    public CursoRequestDTO toRequestDTO (Curso curso){
        if (curso == null){
            return null;
        }

        return CursoRequestDTO.builder()
                .nome(curso.getNome())
                .descricao(curso.getDescricao())
                .cargaHoraria(curso.getCargaHoraria())
                .build();
    }

    public Curso fromResponseDTO(CursoResponseDTO cursoResponseDTO){
        if (cursoResponseDTO == null){
            return null;
        }

        return Curso.builder()
                .id(cursoResponseDTO.getId())
                .nome(cursoResponseDTO.getNome())
                .descricao(cursoResponseDTO.getDescricao())
                .cargaHoraria(cursoResponseDTO.getCargaHoraria())
                .build();
    }
}
