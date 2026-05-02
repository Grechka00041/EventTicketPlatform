package com.grechka.eventticketplatform.controllers;

import com.grechka.eventticketplatform.domain.CreateEventRequest;
import com.grechka.eventticketplatform.domain.dtos.CreateEventRequestDto;
import com.grechka.eventticketplatform.domain.dtos.CreateEventResponseDto;
import com.grechka.eventticketplatform.domain.entities.Event;
import com.grechka.eventticketplatform.mappers.EventMapper;
import com.grechka.eventticketplatform.services.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/event")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final EventMapper eventMapper;

    @PostMapping
    public ResponseEntity<CreateEventResponseDto> createEvent(
            @AuthenticationPrincipal Jwt jwt,
            @Valid @RequestBody CreateEventRequestDto createEventRequestDto){
        CreateEventRequest createEventRequest = eventMapper.fromDto(createEventRequestDto);
        UUID userId = UUID.fromString(jwt.getSubject());
        Event createdEvent = eventService.createEvent(createEventRequest, userId);
        CreateEventResponseDto createdEventResponseDto = eventMapper.toDto(createdEvent);
        return new ResponseEntity<>(createdEventResponseDto, HttpStatus.CREATED);

    }

}
