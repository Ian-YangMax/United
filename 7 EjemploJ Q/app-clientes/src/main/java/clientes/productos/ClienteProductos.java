package clientes.productos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class ClienteProductos {
    @Getter
    @Setter
    private Long id;
    @Getter @Setter private String nombre;
    @Getter @Setter private Double precio;
}
