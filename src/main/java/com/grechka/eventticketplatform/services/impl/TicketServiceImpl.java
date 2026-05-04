package com.grechka.eventticketplatform.services.impl;

import com.grechka.eventticketplatform.domain.entities.Ticket;
import com.grechka.eventticketplatform.repositories.TicketRepository;
import com.grechka.eventticketplatform.services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;

    @Override
    public Page<Ticket> listTicketsForUser(UUID purchaserId, Pageable pageable) {
        return ticketRepository.findByPurchaserId(purchaserId, pageable);
    }

    @Override
    public Optional<Ticket> getTicketForUser(UUID ticketId, UUID userId) {
        return  ticketRepository.findByIdAndPurchaserId(ticketId, userId);
    }


}
