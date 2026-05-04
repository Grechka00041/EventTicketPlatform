package com.grechka.eventticketplatform.services.impl;

import com.grechka.eventticketplatform.domain.entities.QrCode;
import com.grechka.eventticketplatform.domain.entities.Ticket;
import com.grechka.eventticketplatform.domain.entities.TicketValidation;
import com.grechka.eventticketplatform.domain.enums.QrCodeStatusEnum;
import com.grechka.eventticketplatform.domain.enums.TicketValidationMethodEnum;
import com.grechka.eventticketplatform.domain.enums.TicketValidationStatusEnum;
import com.grechka.eventticketplatform.exceptions.QrCodeNotFoundException;
import com.grechka.eventticketplatform.exceptions.TicketNotFoundException;
import com.grechka.eventticketplatform.repositories.QrCodeRepository;
import com.grechka.eventticketplatform.repositories.TicketRepository;
import com.grechka.eventticketplatform.repositories.TicketValidationRepository;
import com.grechka.eventticketplatform.services.TicketValidationService;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class TicketValidationServiceImpl implements TicketValidationService {

    private final TicketValidationRepository ticketValidationRepository;
    private final QrCodeRepository qrCodeRepository;
    private final TicketRepository ticketRepository;

    @Override
    public TicketValidation validateTicketByQrCode(UUID qrCodeId) {
        QrCode qrCode = qrCodeRepository.findByIdAndStatus(qrCodeId, QrCodeStatusEnum.ACTIVE)
                .orElseThrow(() -> new QrCodeNotFoundException(
                        String.format("QR Code with id %s not found", qrCodeId)
                ));

        Ticket ticket = qrCode.getTicket();

        return validateTicket(ticket, TicketValidationMethodEnum.QR_SCAN);
    }

    private @NonNull TicketValidation validateTicket(Ticket ticket, TicketValidationMethodEnum method) {
        TicketValidation ticketValidation = new TicketValidation();
        ticketValidation.setTicket(ticket);
        ticketValidation.setMethod(method);

        TicketValidationStatusEnum ticketValidationStatus = ticket.getValidations().stream()
                .filter(v -> TicketValidationStatusEnum.VALID.equals(v.getStatus()))
                .findFirst()
                .map(v -> TicketValidationStatusEnum.INVALID)
                .orElse(TicketValidationStatusEnum.VALID);

        ticketValidation.setStatus(ticketValidationStatus);
        return ticketValidationRepository.save(ticketValidation);
    }

    @Override
    public TicketValidation validateTicketManually(UUID ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(TicketNotFoundException::new);
        return validateTicket(ticket, TicketValidationMethodEnum.MANUAL);
    }


}
