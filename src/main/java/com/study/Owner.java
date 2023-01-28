package com.study;

import lombok.*;
import org.springframework.stereotype.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component //@Service @Repository @Controller @RestController
public class Owner {

    private String name;

}
