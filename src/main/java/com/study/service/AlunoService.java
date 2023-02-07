package com.study.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.study.domain.dto.AlunoRequestDto;
import com.study.domain.dto.AlunoResponseDto;
import com.study.domain.dto.TodoDto;
import com.study.domain.dto.TutorResponse;
import com.study.mapper.AlunoMapper;
import com.study.model.AlunoModel;
import com.study.repository.AlunoRepository;
import com.study.repository.ProfessorRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class AlunoService {

    //private final Map<Integer, AlunoRequestDto> repository;
    private final AlunoRepository repository;
    private final ProfessorRepository professorRepository;
    private final AlunoMapper mapper;
    private final RestTemplate restTemplate;

    public AlunoResponseDto save(final AlunoRequestDto aluno) {

            return mapper.toResponse(repository.save(mapper.toEntity(aluno)));
    }

    public AlunoResponseDto getById(final int id) {

        var optionalAlunoModel = repository.findById(id);
/*
        return optionalAlunoModel.ifPresentOrElse(
                alunoModel -> mapper.toResponse(alunoModel),
                () -> new AlunoResponseDto());
 */

        if (optionalAlunoModel.isPresent()) {
            return mapper.toResponse(optionalAlunoModel.get());
        }

        return new AlunoResponseDto();


    }

    public List<AlunoResponseDto> getAll() {
        return mapper.toResponse(repository.findAll());
    }

    public AlunoResponseDto update(final int id, final AlunoRequestDto alunoRequest) {
        var optionalAluno = repository.findById(id);

        optionalAluno.orElseThrow(() -> new EntityNotFoundException("Aluno not found."));

        AlunoModel entity = mapper.toEntity(alunoRequest);
        entity.setId(id);

        repository.save(entity);
        return mapper.toResponse(entity);
    }

    public void delete(final int id) {

        repository.deleteById(id);
    }

    @Transactional
    public TutorResponse updateTutor(int idAluno, int idProfessor) {

        log.info("Updating tutor aluno-id: {}, professor-id: {}", idAluno, idProfessor);

        //find entities
        var alunoOptional = repository.findById(idAluno);
        var professorOptional = professorRepository.findById(idProfessor);

        //validate is not empty
        var aluno = alunoOptional.orElseThrow(() -> new EntityNotFoundException("Aluno not found"));
        var professor = professorOptional.orElseThrow(() -> new EntityNotFoundException("Professor not found"));

        //Update
        aluno.setTutor(professor);
        repository.save(aluno);

        return mapper.toResponse(professor);
    }

    public List<AlunoResponseDto> getTutoradosByProfessorId(int idProfessor) {

        log.info("Getting tutorados by professor-id: {}", idProfessor);

        var professorOptional = professorRepository.findById(idProfessor);
        var professor = professorOptional.orElseThrow(() -> new EntityNotFoundException("Professor not found"));

        List<AlunoModel> listOfEntities = repository.findAlunosByTutor(professor);

        return mapper.toResponse(listOfEntities);
    }
    
    public TodoDto generateRandomTodo() {
        return restTemplate
                .getForEntity("https://www.boredapi.com/api/activity", TodoDto.class)
                .getBody();
    }
}
