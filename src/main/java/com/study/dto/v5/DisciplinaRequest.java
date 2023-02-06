package com.study.dto.v5;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.validation.constraints.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class DisciplinaRequest {

    @NotBlank(message = "Name must be not empty or null")
    private String name;
}
