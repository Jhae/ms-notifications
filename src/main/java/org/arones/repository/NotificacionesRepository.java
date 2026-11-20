package org.arones.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.arones.model.NotificacionNtt;

@ApplicationScoped
public class NotificacionesRepository implements PanacheRepository<NotificacionNtt> {
}
