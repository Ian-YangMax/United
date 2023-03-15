package com.distribuida.db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ordencompra")
@NamedQuery(name = "OrdenCompra.findAll",query = "SELECT b FROM Producto b")
@Entity
public class OrdenCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "cliente_id")
    private String cliente_id;
    @Column(name = "producto_id")
    private String producto_id;
    @Column(name = "precio")
    private BigDecimal precio;
}
