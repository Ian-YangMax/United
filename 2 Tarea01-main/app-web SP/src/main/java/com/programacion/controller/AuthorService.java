package com.programacion.controller;

import spark.Request;
import spark.Response;

import java.text.ParseException;
//clase
public interface AuthorService {

    //obtener autores
    String getAuthors(Request request, Response response);
    //ver autores
    String showAddAuthor(Request request, Response response);
    //añadir autores
    String addAuthor(Request request, Response response) throws ParseException;
    //actualizar autor
    String showUpdateAuthor(Request request, Response response) throws ParseException;
    //Eliminar autor
    String deleteAuthor(Request request, Response response);
}
