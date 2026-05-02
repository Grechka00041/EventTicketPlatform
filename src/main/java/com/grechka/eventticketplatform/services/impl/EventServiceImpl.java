package com.grechka.eventticketplatform.services.impl;

import com.grechka.eventticketplatform.domain.CreateEventRequest;
import com.grechka.eventticketplatform.domain.entities.Event;
import com.grechka.eventticketplatform.domain.entities.TicketType;
import com.grechka.eventticketplatform.domain.entities.User;
import com.grechka.eventticketplatform.exceptions.UserNotFoundException;
import com.grechka.eventticketplatform.repositories.EventRepository;
import com.grechka.eventticketplatform.repositories.UserRepository;
import com.grechka.eventticketplatform.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    @Override
    public Event createEvent(CreateEventRequest event, UUID organizerId) {
        User organizer = userRepository.findById(organizerId)
                .orElseThrow(() -> new UserNotFoundException(
                        String.format("User with id %s not found", organizerId)
                ));

        List<TicketType> ticketTypesToCreate = event.getTicketTypes().stream().map(
                ticketType -> {
                    TicketType ticketTypeToCreate = new TicketType();
                    ticketTypeToCreate.setName(ticketType.getName());
                    ticketTypeToCreate.setDescription(ticketType.getDescription());
                    ticketTypeToCreate.setPrice(ticketType.getPrice());
                    ticketTypeToCreate.setTotalAvailable(ticketType.getTotalAvailable());
                    return ticketTypeToCreate;
                }
        ).toList();

        Event eventToCreate = new Event();
        eventToCreate.setName(event.getName());
        eventToCreate.setOrganizer(organizer);
        eventToCreate.setStartTime(event.getStartTime());
        eventToCreate.setEndTime(event.getEndTime());
        eventToCreate.setSalesStart(event.getSalesStart());
        eventToCreate.setSalesEnd(event.getSalesEnd());
        eventToCreate.setStatus(event.getStatus());
        eventToCreate.setTicketTypes(ticketTypesToCreate);

        return eventRepository.save(eventToCreate);
    }
}
