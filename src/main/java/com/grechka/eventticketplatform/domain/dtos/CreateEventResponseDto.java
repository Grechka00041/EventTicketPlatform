package com.grechka.eventticketplatform.domain.dtos;

import com.grechka.eventticketplatform.domain.enums.EventStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEventResponseDto {

    private UUID id;

    private String name;

    private String venue;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private LocalDateTime salesStart;

    private LocalDateTime salesEnd;


    private EventStatusEnum status;

    private List<CreateTicketTypeResponseDto> ticketTypes;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
