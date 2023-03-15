package com.programacion.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book{
        private String _id;
        private String isbn;
        private String title;
        private String author;
        private Double price;
}
