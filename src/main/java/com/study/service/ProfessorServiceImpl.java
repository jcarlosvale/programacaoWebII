package com.study.service;
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
import org.springframework.stereotype.Service;
import com.study.dto.ProfessorResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProfessorServiceImpl implements ProfessorService{
   
    /*TODO: ver com o professor pq não aceitou FINAL */
    private Map<Integer, ProfessorResponse> profRepository;
    
    @Override
    public Boolean save(ProfessorResponse prof){
        if (profRepository.containsKey(prof.getId())){
            log.error("Já existe o id {} na coleção!", prof.getId());
            return false;   
        }else{
            profRepository.put(prof.getId(), prof);
            log.info("Inserido um novo professor {}", prof);
            return true;
        }

    }

    @Override
    public Collection<ProfessorResponse> getAll(){
        if(profRepository.size() == 0){
            return List.of();
        }else{
            return profRepository.values();
        }
        
    }

    @Override
    public List<ProfessorResponse> addAll(List<ProfessorResponse> listProf){
        if(profRepository.size() == 0){
            listProf.forEach(prof -> {
                profRepository.put(prof.getId(), prof);
            });
        }else{
            profRepository.values().forEach(prof -> {
                listProf.forEach(prof2 -> { 
                    if(!prof.getName().equals(prof2.getName())){
                        profRepository.put(prof2.getId(), prof2);
                    }
                });
            });

        }
        return listProf;
    
    }
}