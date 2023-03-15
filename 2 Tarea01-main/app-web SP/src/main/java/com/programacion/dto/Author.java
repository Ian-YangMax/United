package com.programacion.dto;

import lombok.*;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String firstname;

    @Getter
    @Setter
    private String lastname;

}
