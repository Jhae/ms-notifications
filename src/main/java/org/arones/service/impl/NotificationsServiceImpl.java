package org.arones.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.Optional;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.arones.dto.NotifyUserRequestDto;
import org.arones.dto.NotifyUserResponseDto;
import org.arones.mappers.NotificationsMapper;
import org.arones.model.NotificacionNtt;
import org.arones.repository.NotificacionesRepository;
import org.arones.service.NotificationsService;

@ApplicationScoped
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class NotificationsServiceImpl implements NotificationsService {

    private final NotificacionesRepository notificacionesRepository;
    private final NotificationsMapper notificationsMapper;

    @Override
    @Transactional
    public Optional<NotifyUserResponseDto> notifyUser(NotifyUserRequestDto notifyUserRequestDto) {
        log.info("inciando metodo input notifyUserRequestDto: {}", notifyUserRequestDto);

        NotificacionNtt notificacionNttToSave = NotificacionNtt.builder()
                .evento(notifyUserRequestDto.getEvento())
                .payload("")
                .destino(notifyUserRequestDto.getDestino())
                .estado("OK")
                .build();

        notificacionesRepository.persist(notificacionNttToSave);
        NotifyUserResponseDto notifyUserResponseDto = notificationsMapper.fromNttToResponse(notificacionNttToSave);

        return Optional.of(notifyUserResponseDto);
    }
}
