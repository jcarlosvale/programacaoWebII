package com.study.dto.v3;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class TutorResponse {

    private String tutor;

    private String atualizacao;
}
