package org.example.config;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import jakarta.ws.rs.Produces;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperties;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.example.db.Book;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.beans.JavaBean;
import java.sql.Connection;
import java.sql.DriverManager;
import org.jdbi.v3.core.Jdbi;


//Deben ser componentes CDI

@ApplicationScoped
public class DbConfig {
    @Inject
    @ConfigProperty(name="db.user")
    String dbuser;

    @Inject
    @ConfigProperty(name="db.password")
    String dbpassword;

    @Inject
    @ConfigProperty(name="db.url")
    String dburl;

    private Jdbi jdbi;

    @PostConstruct
    public void init(){
        jdbi = Jdbi.create(dburl,dbuser,dbpassword);
        jdbi.installPlugin(new SqlObjectPlugin());
    }

    @Produces
    @ApplicationScoped
    public Handle conection(){
        jdbi = Jdbi.create(dburl,dbuser,dbpassword);
        return jdbi.open();
    }
}
