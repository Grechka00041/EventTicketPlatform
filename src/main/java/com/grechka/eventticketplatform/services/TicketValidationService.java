package com.grechka.eventticketplatform.services;


import com.grechka.eventticketplatform.domain.entities.TicketValidation;


import java.util.UUID;

public interface TicketValidationService {
    TicketValidation validateTicketByQrCode(UUID qrCodeId);
    TicketValidation validateTicketManually(UUID ticketId);
}
