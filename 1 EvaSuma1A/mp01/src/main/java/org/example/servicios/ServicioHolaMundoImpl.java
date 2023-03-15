package org.example.servicios;

import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDateTime;

@ApplicationScoped
public class ServicioHolaMundoImpl implements ServicioHolaMundo{

    @Override
    public String Hola(){
        return "Hola --> la hora es: " + LocalDateTime.now();
    }

}
