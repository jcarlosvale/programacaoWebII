package com.study.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //toString, get/set, requiredargs, equals, hashcode
@NoArgsConstructor // construtor sem parametros
@AllArgsConstructor // construtor com todos os parametros
@Builder //design pattern de construtor
public class Cor {

    private int id; //identificacao

    private String descricao;
}


