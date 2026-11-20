package org.arones.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.arones.dto.NotifyUserRequestDto;
import org.arones.dto.NotifyUserResponseDto;
import org.arones.service.NotificationsService;
import org.jboss.resteasy.reactive.RestResponse;

@Path("/notifications")
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class NotificacionReourceImpl {

    private final NotificationsService notificationsService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse<NotifyUserResponseDto> notifyUser(NotifyUserRequestDto notifyUserRequestDto){
        log.info("Inicio metodo controller POST");
        log.info("input notifyUserRequestDto: {}", notifyUserRequestDto);

        return notificationsService.notifyUser(notifyUserRequestDto)
                .map(notifyUserResponseDto -> {
                    return RestResponse.ResponseBuilder
                            .create(RestResponse.Status.CREATED, notifyUserResponseDto)
                            .location(URI.create("/notifications/" + notifyUserResponseDto.getId()))
                            .build();
                }).orElse(RestResponse.status(RestResponse.Status.BAD_REQUEST));
    }

}
