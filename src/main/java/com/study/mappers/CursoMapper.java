package com.study.mappers;

import com.study.domain.dto.request.CursoRequestDTO;
import com.study.domain.dto.response.CursoResponseDTO;
import com.study.domain.dto.response.ProfessorResponseDTO;
import com.study.entity.Disciplina;
import com.study.entity.Professor;

import org.springframework.stereotype.Component;

@Component
public class CursoMapper {
    public CursoResponseDTO toResponseDTO (Disciplina curso){
        if (curso == null){
            return null;
        }

        return CursoResponseDTO.builder()
                .nome(curso.getNome())
                .descricao(curso.getDescricao())
                .cargaHoraria(curso.getCargaHoraria())
                .professor(this.toDTO(curso.getTitular()))
                .build();
    }

    private ProfessorResponseDTO toDTO(Professor titular) {
		if (titular == null)
			return null;
		return ProfessorResponseDTO.builder().id(titular.getId()).nome(titular.getNome())
				.sexo(titular.getSexo())
				.titulo(titular.getTitulo())
				.build();
		
	}

	public Disciplina fromResponseDTO(CursoResponseDTO cursoResponseDTO){
        if (cursoResponseDTO == null){
            return null;
        }

        return Disciplina.builder()
                .id(cursoResponseDTO.getId())
                .nome(cursoResponseDTO.getNome())
                .descricao(cursoResponseDTO.getDescricao())
                .cargaHoraria(cursoResponseDTO.getCargaHoraria())
                .build();
    }
}
