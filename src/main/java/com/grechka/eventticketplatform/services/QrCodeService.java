package com.grechka.eventticketplatform.services;

import com.grechka.eventticketplatform.domain.entities.QrCode;
import com.grechka.eventticketplatform.domain.entities.Ticket;

public interface QrCodeService {

    QrCode generateQrCode(Ticket ticket);
}
