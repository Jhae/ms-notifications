package org.arones.service;

import org.arones.dto.NotifyUserRequestDto;
import org.arones.dto.NotifyUserResponseDto;

import java.util.Optional;


public interface NotificationsService {

    Optional<NotifyUserResponseDto> notifyUser(NotifyUserRequestDto notifyUserRequestDto);
}
