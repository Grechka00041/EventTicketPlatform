package com.grechka.eventticketplatform.domain.dtos;

import com.grechka.eventticketplatform.domain.enums.EventStatusEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEventRequestDto {

    @NotNull(message = "Event id must be provided")
    private UUID id;

    @NotBlank(message = "Event name is required")
    private String name;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @NotBlank(message = "Venue info is required")
    private String venue;

    private LocalDateTime salesStart;

    private LocalDateTime salesEnd;

    @NotNull(message = "Event status must be provided")
    private EventStatusEnum status;

    @NotEmpty(message = "At least one ticket type must be provided")
    @Valid
    private List<UpdateTicketTypeRequestDto> ticketTypes;
}
