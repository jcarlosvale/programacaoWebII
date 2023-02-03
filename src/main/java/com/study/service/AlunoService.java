package com.study.service;

import com.study.dto.AlunoRequestDto;
import com.study.dto.AlunoResponseDto;
import com.study.mapper.AlunoMapper;
import com.study.model.Aluno;
import com.study.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final AlunoMapper mapper;


    public List<AlunoResponseDto> retrieveAll() {
        List<Aluno> alunos = alunoRepository.findAll();
        return mapper.toResponse(alunos);
    }

    public AlunoResponseDto getById(int id) {
        Optional<Aluno> alunoOptional = alunoRepository.findById(id);
        if(alunoOptional.isPresent()) {
            return mapper.toResponse(alunoOptional.get());
        }
        return  new AlunoResponseDto();
    }

    public AlunoResponseDto save(AlunoRequestDto request) {
        Aluno aluno = alunoRepository.save(mapper.toEntity(request));
        return mapper.toResponse(aluno);
    }

    public AlunoResponseDto update(int id, AlunoRequestDto request) {
        Optional<Aluno> alunoOptional = alunoRepository.findById(id);
        alunoOptional.orElseThrow(() -> new EntityNotFoundException("Aluno not found."));

        Aluno aluno = mapper.toEntity(request);
        aluno.setId(id);

        alunoRepository.save(aluno);
        return mapper.toResponse(aluno);
    }

    public void delete(int id) {
        log.info("Deleting professor id - {}", id);
        alunoRepository.deleteById(id);
    }

}
