package com.grechka.eventticketplatform.services.impl;

import com.grechka.eventticketplatform.domain.entities.Ticket;
import com.grechka.eventticketplatform.domain.entities.TicketType;
import com.grechka.eventticketplatform.domain.entities.User;
import com.grechka.eventticketplatform.domain.enums.TicketStatusEnum;
import com.grechka.eventticketplatform.exceptions.TicketSoldOutException;
import com.grechka.eventticketplatform.exceptions.TicketTypeNotFoundException;
import com.grechka.eventticketplatform.exceptions.UserNotFoundException;
import com.grechka.eventticketplatform.repositories.TicketRepository;
import com.grechka.eventticketplatform.repositories.TicketTypeRepository;
import com.grechka.eventticketplatform.repositories.UserRepository;
import com.grechka.eventticketplatform.services.QrCodeService;
import com.grechka.eventticketplatform.services.TicketTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class TicketTypeServiceImpl implements TicketTypeService {

    private final UserRepository userRepository;
    private  final TicketRepository ticketRepository;
    private  final TicketTypeRepository ticketTypeRepository;
    private final QrCodeService qrCodeService;

    @Override
    public Ticket purchaseTicket(UUID userId, UUID ticketTypeId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(
                        String.format("User with id %s was not found", userId)
                ));

        TicketType ticketType = ticketTypeRepository.findByIdWithLock(ticketTypeId)
                .orElseThrow(() -> new TicketTypeNotFoundException(
                        String.format("Ticket type with id %s was not found", ticketTypeId)
                ));
        int purchasedTickets = ticketRepository.countByTicketTypeId(ticketTypeId);
        Integer totalAvailable = ticketType.getTotalAvailable();
        if(purchasedTickets + 1 > totalAvailable) {
            throw new TicketSoldOutException(
                    String.format("Ticket type with id %s was sold out", ticketTypeId)
            );
        }
        Ticket ticket = new Ticket();
        ticket.setStatus(TicketStatusEnum.PURCHASED);
        ticket.setType(ticketType);
        ticket.setPurchaser(user);
        Ticket savedTicket = ticketRepository.save(ticket);
        qrCodeService.generateQrCode(savedTicket);
        return ticketRepository.save(savedTicket);

    }
}
