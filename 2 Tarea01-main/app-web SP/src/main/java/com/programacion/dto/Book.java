package com.programacion.dto;
import lombok.*;

import java.math.BigDecimal;



@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book{

        @Getter
        @Setter
        private Integer id;

        @Getter
        @Setter
        private String isbn;

        @Getter
        @Setter
        private String title;

        @Getter
        @Setter
        private Integer author;

        @Getter
        @Setter
        private BigDecimal price;
}
