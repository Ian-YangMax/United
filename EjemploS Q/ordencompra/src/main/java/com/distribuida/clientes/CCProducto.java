package com.distribuida.clientes;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class CCProducto {
    @Getter
    @Setter
    private Long id;
    @Getter @Setter private String nombre;
    @Getter @Setter private Double precio;


}
