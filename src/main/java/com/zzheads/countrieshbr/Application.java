package com.zzheads.countrieshbr;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

/**
 * Created by zzheads on 23.07.2016.
 */
@EnableAutoConfiguration
@ComponentScan
public class Application {

    // Hold a reusable reference to a SessionFactory (since we need only one)
    public static final SessionFactory sessionFactory = buildSessionFactory();
    private static SessionFactory buildSessionFactory() {
        // Create a StandartServiceRegistry
        final ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        return new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
