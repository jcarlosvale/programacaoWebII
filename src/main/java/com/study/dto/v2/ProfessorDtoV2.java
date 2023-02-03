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

    @NotBlank(message = "Name must be not empty or null")
    @Size(min = 2, message = "Minimum name length 2 characters")
    private String name;

    @Email(message = "Email not valid")
    private String email;

    @CPF(message = "CPF not valid")
    private String cpf;
}
