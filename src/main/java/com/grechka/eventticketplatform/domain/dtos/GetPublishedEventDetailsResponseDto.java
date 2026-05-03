package com.grechka.eventticketplatform.domain.dtos;

import com.grechka.eventticketplatform.domain.enums.EventStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPublishedEventDetailsResponseDto {
    private UUID id;
    private String name;
    private String venue;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<GetPublishedEventTicketTypeResponseDto> ticketTypes = new ArrayList<>();
}
