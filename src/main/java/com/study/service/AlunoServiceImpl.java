package com.study.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.study.dto.AlunoResponse;
import com.study.entity.AlunoEntity;
import com.study.mapper.AlunoMapper;
import com.study.repository.AlunoRepository;
import com.study.repository.ProfessorRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AlunoServiceImpl implements AlunoService {

    private final AlunoRepository alunoRepository;
    private final AlunoMapper alunoMapper;
    private final ProfessorRepository professorRepository;
   

    @Override
    @Transactional
    public void delete(int id) {
        alunoRepository.delete(alunoRepository.getReferenceById(id));
    }

    @Override
    @Transactional
    public List<AlunoResponse> getAll() {
        List<AlunoEntity> aList = alunoRepository.findAll();
        return alunoMapper.toResponse(aList);
    }

    @Override
    @Transactional
    public AlunoResponse getById(int id) {
        
        return alunoMapper.toResponse(alunoRepository.findById(id).get());
    }

    @Override
    @Transactional
    public List<AlunoResponse> getByPrefix(String prefixo) {
        final List<AlunoEntity> aList = alunoRepository.findAll();
        List<AlunoEntity> lstEntity = new ArrayList<>();
        aList.forEach(p -> {
            if(p.getNome().startsWith(prefixo)){
                lstEntity.add(p);
            }
        }); 
        return alunoMapper.toResponse(lstEntity);
    }

    @Override
    @Transactional
    public AlunoResponse save(AlunoResponse aluno) {
        
        Objects.requireNonNull(aluno, "Aluno cannot be null! ");
        log.info("Saving Aluno - {}", aluno);
        
        var aEntity = AlunoEntity.builder()
        .nome(aluno.getNome())
        .email(aluno.getEmail())
        .cpf(aluno.getCpf())
        .idade(aluno.getIdade())
        .matricula(aluno.getMatricula())
        .sexo(aluno.getSexo())
        .build();
        
        aEntity = alunoRepository.save(aEntity);
        return alunoMapper.toResponse(aEntity);
    }

    @Override
    @Transactional
    public AlunoResponse update(int id, AlunoResponse alunoResponse) {
     
        var aluno = alunoRepository.findById(id).get();

        log.info("Atualizando aluno ID - {} ", id);
        
        aluno.setNome(alunoResponse.getNome());
        aluno.setEmail(alunoResponse.getEmail());
        aluno.setCpf(alunoResponse.getCpf());
        aluno.setIdade(alunoResponse.getIdade());
        aluno.setMatricula(alunoResponse.getMatricula());
        aluno.setSexo(alunoResponse.getSexo());

        alunoMapper.toResponse(alunoRepository.save(aluno));

        return alunoMapper.toResponse(aluno);
    }    

    public AlunoResponse updateTutor(final Integer idAluno,  final Integer idTutor){

        var aluno = alunoRepository.findById(idAluno).get();
        var prof = professorRepository.findById(idTutor).get();

        aluno.setTutor(prof);
        
        return alunoMapper.toResponse(alunoRepository.save(aluno));
    }
}
