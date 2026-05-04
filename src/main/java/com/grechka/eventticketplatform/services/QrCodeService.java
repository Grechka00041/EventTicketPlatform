package com.grechka.eventticketplatform.services;

import com.grechka.eventticketplatform.domain.entities.QrCode;
import com.grechka.eventticketplatform.domain.entities.Ticket;

import java.util.UUID;

public interface QrCodeService {

    QrCode generateQrCode(Ticket ticket);
    byte[] getQrCodeImageForUserAndTicket(UUID userId, UUID ticketId);
}
