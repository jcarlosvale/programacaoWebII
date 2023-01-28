package com.study.service;
import java.util.ArrayList;
import java.util.Collection;
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
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.study.domain.dto.ProfessorDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
/*
 * 
 * Spring @Service annotation is used with classes that provide some business functionalities. 
 * Spring context will autodetect these classes when annotation-based configuration and classpath scanning is used.
 * 
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ProfessorService {
   
    private final Map<Integer, ProfessorDto> profRepository;
    
    public Boolean save(ProfessorDto prof){
        if (profRepository.containsKey(prof.getId())){
            log.error("Já existe o id {} na coleção!", prof.getId());
            return false;   
        }else{
            profRepository.put(prof.getId(), prof);
            log.info("Inserido um novo professor {}", prof);
            return true;
        }

    }

    public Collection<ProfessorDto> getAll(){
        if(profRepository.size() == 0){
            return List.of();
        }else{
            return profRepository.values();
        }
        
    }

    /*public List<ProfessorDto> addAll(List<ProfessorDto> listProf){
        if(profRepository.size() == 0){
            listProf.forEach(prof -> {
                profRepository.put(prof.getId(), prof);
            });
        }else{
            profRepository.values().forEach(prof -> {
                listProf.forEach(prof2 -> { 
                    if(!prof.getNome().equals(prof2.getNome())){
                        profRepository.put(prof.getId(), prof);
                    }
                });
            });

        }
        return listProf;        
    }*/
    public List<ProfessorDto> addAll(List<ProfessorDto> listProf){
        if(profRepository.size() == 0){
            listProf.forEach(prof -> {
                profRepository.put(prof.getId(), prof);
            });
        }else{
            profRepository.values().forEach(prof -> {
                listProf.forEach(prof2 -> { 
                    if(!prof.getNome().equals(prof2.getNome())){
                        profRepository.put(prof2.getId(), prof2);
                    }
                });
            });

        }
        return listProf;
        
    }
    
}
