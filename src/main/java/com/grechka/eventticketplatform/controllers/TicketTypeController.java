package com.grechka.eventticketplatform.controllers;

import com.grechka.eventticketplatform.domain.entities.TicketType;
import com.grechka.eventticketplatform.services.TicketTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.grechka.eventticketplatform.util.JwtUtil.parseUserId;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/events/{eventId}/ticket-types")
public class TicketTypeController {

    private final TicketTypeService ticketTypeService;

    @PostMapping(path = "/{ticketTypeId}/tickets")
    public ResponseEntity<Void> purchaseTicket(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable("ticketTypeId") UUID ticketTypeId){

        ticketTypeService.purchaseTicket(parseUserId(jwt), ticketTypeId);
        return  ResponseEntity.noContent().build();
    }

}
