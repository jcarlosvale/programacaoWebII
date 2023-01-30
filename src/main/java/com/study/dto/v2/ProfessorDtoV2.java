package com.study.dto.v2;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.hibernate.validator.constraints.br.*;

import javax.validation.constraints.*;

/**
 * Using Beans Validation
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class ProfessorDtoV2 {

    @Positive
    private int id;

    @NotBlank
    @Size(min = 2)
    private String name;

    @Email
    private String email;

    @CPF
    private String cpf;
}
