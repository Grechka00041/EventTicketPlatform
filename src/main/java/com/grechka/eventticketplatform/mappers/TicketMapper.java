package com.grechka.eventticketplatform.mappers;

import com.grechka.eventticketplatform.domain.dtos.GetTicketResponseDto;
import com.grechka.eventticketplatform.domain.dtos.ListTicketResponseDto;
import com.grechka.eventticketplatform.domain.dtos.ListTicketTicketTypeResponseDto;
import com.grechka.eventticketplatform.domain.entities.Ticket;
import com.grechka.eventticketplatform.domain.entities.TicketType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TicketMapper {
    ListTicketTicketTypeResponseDto toTicketTypeResponseDto(TicketType ticketType);
    ListTicketResponseDto toListTicketResponseDto(Ticket ticket);
    @Mapping(target = "price", source = "ticket.ticketType.price")
    @Mapping(target = "eventName", source = "ticket.ticketType.event.name")
    @Mapping(target = "eventVenue", source = "ticket.ticketType.event.venue")
    @Mapping(target = "eventStart", source = "ticket.ticketType.event.start")
    @Mapping(target = "eventEnd", source = "ticket.ticketType.event.end")
    GetTicketResponseDto  toGetTicketResponseDto(Ticket ticket);
}
