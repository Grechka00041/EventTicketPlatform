package com.grechka.eventticketplatform.services;

import com.grechka.eventticketplatform.domain.entities.Ticket;

import java.util.UUID;

public interface TicketTypeService {
    Ticket purchaseTicket(UUID userId, UUID ticketTypeId);
}
