package com.distribuida.db;

import lombok.Data;

@Data
public class Book {
    private Integer Id;
    private String isbn;
    private String title;
    private String author;
    private Double price;
}