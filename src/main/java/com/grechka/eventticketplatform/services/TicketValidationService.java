package com.grechka.eventticketplatform.services;


import com.grechka.eventticketplatform.domain.entities.TicketValidation;
import com.grechka.eventticketplatform.exceptions.QrCodeNotFoundException;

import java.util.UUID;

public interface TicketValidationService {
    TicketValidation validateTicketByQrCode(UUID qrCodeId);
    TicketValidation validateTicketManually(UUID ticketId);
}
