package org.arones.config;

import io.quarkus.runtime.Startup;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.avro.util.ClassSecurityValidator;

@Startup
@ApplicationScoped
public class AvroApiSecurityConfig {

    static {
        // Configuramos el validador global combinando las clases por defecto con tu paquete o clase de eventos
        ClassSecurityValidator.setGlobal(
                ClassSecurityValidator.composite(
                        ClassSecurityValidator.DEFAULT,
                        ClassSecurityValidator.SYSTEM_PROPERTIES
                )
        );
    }
}