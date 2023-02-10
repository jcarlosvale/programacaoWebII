package com.study.service;
import java.util.ArrayList;
/*
 * {
    "id": 1,
    "nome": "Professor Ubirajara Guadalahara",
    "genero": "Masculino"
}
{
    "id": 2,
    "nome": "Professora Marieta Zueta",
    "genero": "Feminino"
}
{
    "id": 3,
    "nome": "Professora Emília Manilha",
    "genero": "Feminino"
}
{
    "id": 4,
    "nome": "Professor José Alencar dos Poderes",
    "genero": "Masculino"
}
 */
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.study.dto.ProfessorRequest;
import com.study.dto.ProfessorResponse;
import com.study.mapper.ProfessorMapper;
import com.study.repository.ProfessorRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProfessorServiceImpl implements ProfessorService{

    private final ProfessorRepository profRepository;
    private final ProfessorMapper profMapper;
    
    @Override
    public ProfessorResponse save(ProfessorRequest professor){

            Objects.requireNonNull(professor, "Request cannot be null!");
            log.info("Saving Professor - {}", professor);
            return profMapper.toResponse(profRepository.save(profMapper.toEntity(professor)));
        }
    

    @Override
    public List<ProfessorResponse> getAll(){
        log.info("Listing Professors...");    
        return profMapper.toResponse(profRepository.findAll());
    }
    
    @Override
    public List<ProfessorResponse> addAll(List<ProfessorRequest> listProf){
        
        List<ProfessorResponse> lstRet = new ArrayList<>();

        Objects.requireNonNull(listProf, "All list of Professors cannot be null!");
        log.info("Adding a list ofProfessors....");
        listProf.forEach( prof ->  {
            lstRet.add(profMapper.toResponse(profMapper.toEntity(prof)));    
        });
        return lstRet;
        }


        @Override
        public ProfessorResponse getById(Integer id){
            var prof = profRepository.findById(id).get();
            return profMapper.toResponse(prof);
        }
}