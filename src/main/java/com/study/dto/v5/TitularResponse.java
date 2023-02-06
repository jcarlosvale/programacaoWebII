package com.study.dto.v5;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class TitularResponse {

    private String titular;

    private String atualizacao;
}
