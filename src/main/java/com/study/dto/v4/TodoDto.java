package com.study.dto.v4;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class TodoDto {

    @JsonProperty(value="activity")
    private String description;

}
