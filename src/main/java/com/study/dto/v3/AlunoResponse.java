package com.study.dto.v3;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class AlunoResponse {

    private int id;

    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String tutor;
}
