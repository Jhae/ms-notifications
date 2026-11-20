package org.arones.mappers;

import org.arones.dto.NotifyUserResponseDto;
import org.arones.model.NotificacionNtt;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper( componentModel = MappingConstants.ComponentModel.JAKARTA)
public interface NotificationsMapper {

    NotifyUserResponseDto fromNttToResponse(NotificacionNtt notificacionNtt);
}
