package com.grechka.eventticketplatform.controllers;

import com.grechka.eventticketplatform.domain.CreateEventRequest;
import com.grechka.eventticketplatform.domain.UpdateEventRequest;
import com.grechka.eventticketplatform.domain.dtos.*;
import com.grechka.eventticketplatform.domain.entities.Event;
import com.grechka.eventticketplatform.mappers.EventMapper;
import com.grechka.eventticketplatform.services.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final EventMapper eventMapper;

    @PostMapping
    public ResponseEntity<CreateEventResponseDto> createEvent(
            @AuthenticationPrincipal Jwt jwt,
            @Valid @RequestBody CreateEventRequestDto createEventRequestDto){
        CreateEventRequest createEventRequest = eventMapper.fromDto(createEventRequestDto);
        UUID userId = parseUserId(jwt);
        Event createdEvent = eventService.createEvent(createEventRequest, userId);
        CreateEventResponseDto createdEventResponseDto = eventMapper.toDto(createdEvent);
        return new ResponseEntity<>(createdEventResponseDto, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{eventId}")
    public ResponseEntity<UpdateEventResponseDto> updateEvent(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable UUID eventId,
            @Valid @RequestBody UpdateEventRequestDto updateEventRequestDto){
        UpdateEventRequest updateEventRequest = eventMapper.fromDto(updateEventRequestDto);
        UUID userId = parseUserId(jwt);
        Event updatedEvent = eventService.updateEventForOrganizer(userId, eventId, updateEventRequest);
        UpdateEventResponseDto updatedEventResponseDto = eventMapper.toUpdateEventResponseDto(updatedEvent);
        return ResponseEntity.ok(updatedEventResponseDto);
    }

    @GetMapping
    public ResponseEntity<Page<ListEventResponseDto>> listEvents(
            @AuthenticationPrincipal Jwt jwt,
            Pageable pageable
    ){
        UUID userId = parseUserId(jwt);
        Page<Event> events = eventService.listEventsForOrganizer(userId, pageable);
        return ResponseEntity.ok(events.map(eventMapper::toListEventResponseDto));
    }

    @GetMapping(path = "/{eventId}")
    public ResponseEntity<GetEventDetailsResponseDto> getEvent(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable UUID eventId
    ){
        UUID userId = parseUserId(jwt);
        return eventService.getEventForOrganizer(userId, eventId)
                .map(eventMapper::toGetEventDetailsResponseDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/{eventId}")
    public ResponseEntity<Void> deleteEvent(
            @AuthenticationPrincipal Jwt jwt, @PathVariable UUID eventId
    ){
        UUID userId = parseUserId(jwt);
        eventService.deleteEventForOrganizer(userId, eventId);
        return ResponseEntity.noContent().build();
    }

    private UUID parseUserId(Jwt jwt) {

        return UUID.fromString(jwt.getSubject());
    }



}
