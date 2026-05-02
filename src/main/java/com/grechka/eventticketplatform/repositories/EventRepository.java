package com.grechka.eventticketplatform.repositories;

import com.grechka.eventticketplatform.domain.entities.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
    public Page<Event> findByOrganizerId(UUID organizerId, Pageable pageable);
    public Optional<Event> findByIdAndOrganizerId(UUID id, UUID organizerId);
}
