package com.grechka.eventticketplatform.mappers;

import com.grechka.eventticketplatform.domain.CreateEventRequest;
import com.grechka.eventticketplatform.domain.CreateTicketTypeRequest;
import com.grechka.eventticketplatform.domain.dtos.CreateEventRequestDto;
import com.grechka.eventticketplatform.domain.dtos.CreateEventResponseDto;
import com.grechka.eventticketplatform.domain.dtos.CreateTicketTypeRequestDto;
import com.grechka.eventticketplatform.domain.entities.Event;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventMapper {
    CreateTicketTypeRequest fromDto(CreateTicketTypeRequestDto dto);

    CreateEventRequest fromDto(CreateEventRequestDto dto);

    CreateEventResponseDto toDto(Event event);


}
