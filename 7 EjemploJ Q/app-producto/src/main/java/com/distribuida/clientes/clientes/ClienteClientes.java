package com.distribuida.clientes.clientes;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class ClienteClientes {
    @Getter @Setter private Long id;
    @Getter @Setter private String nombre;
    @Getter @Setter private String apellido;
    @Getter @Setter private String direccion;
}
