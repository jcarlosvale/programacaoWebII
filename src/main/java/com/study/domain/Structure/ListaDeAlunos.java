package com.study.domain.Structure;
import com.study.domain.dto.*;
import java.util.*;


public class ListaDeAlunos {
    
    List<AlunoDto> lstAlunos = new ArrayList<AlunoDto>();
    private int contator;

    ListaDeAlunos(){
        this.contator = 0;
    }

    public List<AlunoDto> getLstAlunos() {
        return lstAlunos;
    }

    public void setLstAlunos(List<AlunoDto> lstAlunos) {
        this.lstAlunos = lstAlunos;
    }

    public Boolean addNewAluno(Integer id, String nome, Integer idade, String matricula, String sexo){

        this.contator +=1;
        AlunoDto aluno = new AlunoDto(id, nome, idade, matricula, sexo);
        lstAlunos.add(this.contator, aluno);

        return true;
    }

}
