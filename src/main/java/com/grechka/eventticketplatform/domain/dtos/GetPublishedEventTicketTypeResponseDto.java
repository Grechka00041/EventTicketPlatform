package com.grechka.eventticketplatform.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPublishedEventTicketTypeResponseDto {
    private UUID id;
    private String name;
    private String description;
    private Double price;
}
