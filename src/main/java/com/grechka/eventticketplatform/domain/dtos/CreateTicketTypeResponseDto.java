package com.grechka.eventticketplatform.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTicketTypeResponseDto {
    private UUID id;

    private String name;

    private String description;

    private Double price;

    private Integer totalAvailable;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
