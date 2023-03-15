package com.distribuida.db;

import com.distribuida.db.Productos;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductoRepository implements PanacheRepository<Productos> {
}
