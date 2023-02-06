package com.study.dto.v5;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class DisciplinaResponse {

    private int id;

    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String titular;

    private String dateTime;

}
