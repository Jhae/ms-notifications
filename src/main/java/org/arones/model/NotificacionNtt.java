package org.arones.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Entity
@Table(name = "notificaciones")
@NoArgsConstructor @AllArgsConstructor @Builder
@Data
public class NotificacionNtt {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String evento;
    private String payload;
    private String destino;
    private String estado;
    @CreationTimestamp
    private OffsetDateTime fecha;
}
