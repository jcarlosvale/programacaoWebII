package com.study.service;

import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.study.dto.DisciplinaResponse;
import com.study.mapper.DisciplinaMapper;
import com.study.repository.DisciplinaRepository;
import com.study.repository.ProfessorRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class DisciplinaServiceImpl implements DisciplinaService{

    @Autowired
    private final DisciplinaRepository disciplinaRepository;
    
    @Autowired
    private final ProfessorRepository professorRepository;
    
    private final DisciplinaMapper disciplinaMapper;
    
    
    @Override
    @Transactional
    public DisciplinaResponse save(DisciplinaResponse disciplina){

            Objects.requireNonNull(disciplina, "Request cannot be null!");
            log.info("Saving Disciplina - {}", disciplina);
            return disciplinaMapper.toResponse(disciplinaRepository.save(disciplinaMapper.toEntity(disciplina)));
        }
    
    @Override
    @Transactional
    public List<DisciplinaResponse> getAll(){
        log.info("Listing Disciplinas...");    
        return disciplinaMapper.toResponse(disciplinaRepository.findAll());
    }
    
    @Override
    @Transactional
    public DisciplinaResponse getById(Integer id){
        var disciplina = disciplinaRepository.findById(id).get();
        return disciplinaMapper.toResponse(disciplina);
    }


    @Override
    @Transactional
    public DisciplinaResponse updateTitular(final Integer idDisciplina, final Integer idTitular){
        var disciplina = disciplinaRepository.findById(idDisciplina).get();
        var titular = professorRepository.findById(idTitular).get();

        disciplina.setTitular(titular);
        
        return disciplinaMapper.toResponse(disciplinaRepository.save(disciplina));
    }
}