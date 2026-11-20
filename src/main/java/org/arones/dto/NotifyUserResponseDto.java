package org.arones.dto;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class NotifyUserResponseDto {

    private Long id;
    private String evento;
    private String payload;
    private String destino;
    private String estado;
    private OffsetDateTime fecha;
}
