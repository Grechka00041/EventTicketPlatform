package com.grechka.eventticketplatform.repositories;

import com.grechka.eventticketplatform.domain.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
}
