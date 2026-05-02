package com.grechka.eventticketplatform.services;

import com.grechka.eventticketplatform.domain.CreateEventRequest;
import com.grechka.eventticketplatform.domain.entities.Event;

import java.util.UUID;


public interface EventService {
    Event createEvent(CreateEventRequest event, UUID organizerId);
}
