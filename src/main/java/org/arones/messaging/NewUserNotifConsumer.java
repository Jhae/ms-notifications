package org.arones.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.users.events.CreatedNewUserEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.arones.model.NotificacionNtt;
import org.arones.repository.NotificacionesRepository;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

@ApplicationScoped
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class NewUserNotifConsumer {

    private final ObjectMapper objectMapper;
    private final NotificacionesRepository notificacionesRepository;

    @Incoming("new-user-topic-in")
    @Outgoing("new-user-notif-topic-out")
    @Transactional
    public String handleNewUserEvent(final CreatedNewUserEvent event) throws JsonProcessingException {
//      UsuarioDto usuario = objectMapper.readValue(jsonString, UsuarioDto.class);
//      JsonNode jsonNode = objectMapper.readTree(newUsuarioPayload);
//      idUsuario = jsonNode.has("id")? jsonNode.get("id").asLong(): null;
//      notificacionNtt = NotificacionNtt.builder()
//                    .destino(jsonNode.has("mail") ? jsonNode.get("mail").asText() : null)
        log.info("Iniciando... handleNewUserEvent para con payload: {}", event);
        NotificacionNtt notificacionNtt = NotificacionNtt.builder()
            .evento("Nuevo Usuario Creado")
            .payload(event.toString())
            .destino(event.getMail().toString())
            .estado("OK")
            .build();

        // Guardar en BD
        notificacionesRepository.persist(notificacionNtt);

        log.info("Fin de handleNewUserEvent :");

        String messageToSend;
        try {
            Map<String, String> bodyToSend = new HashMap<>();
            bodyToSend.put("idUsuario", String.valueOf(event.getId()));
            bodyToSend.put("status", "OK");

            messageToSend = objectMapper.writeValueAsString(bodyToSend);
            log.info("enviando a kafka: {}", messageToSend);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return messageToSend;
    }

}
