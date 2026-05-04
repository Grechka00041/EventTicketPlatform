package com.grechka.eventticketplatform.controllers;

import com.grechka.eventticketplatform.domain.dtos.TicketValidationRequestDto;
import com.grechka.eventticketplatform.domain.dtos.TicketValidationResponseDto;
import com.grechka.eventticketplatform.domain.entities.TicketValidation;
import com.grechka.eventticketplatform.domain.enums.TicketValidationMethodEnum;
import com.grechka.eventticketplatform.mappers.TicketValidationMapper;
import com.grechka.eventticketplatform.services.TicketValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/ticket-validations")
public class TicketValidationController {
    private final TicketValidationService ticketValidationService;
    private final TicketValidationMapper ticketValidationMapper;

    @PostMapping
    public ResponseEntity<TicketValidationResponseDto> validateTicket(
            @RequestBody TicketValidationRequestDto ticketValidationRequestDto
    ){
        TicketValidation ticketValidation;
        TicketValidationMethodEnum method = ticketValidationRequestDto.getMethod();
        if(TicketValidationMethodEnum.MANUAL.equals(method)){
            ticketValidation = ticketValidationService.validateTicketManually(ticketValidationRequestDto.getId());
        }else{
            ticketValidation = ticketValidationService.validateTicketByQrCode(ticketValidationRequestDto.getId());
        }
        return ResponseEntity.ok(
                ticketValidationMapper.toTicketValidationResponseDto(ticketValidation)
        );
    }
}
