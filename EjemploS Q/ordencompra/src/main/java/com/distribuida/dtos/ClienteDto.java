package com.distribuida.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto {
    private Integer id;
    private String nombre;
    private String apellido;
    private String direccion;

    private String ordenproducto;
}
