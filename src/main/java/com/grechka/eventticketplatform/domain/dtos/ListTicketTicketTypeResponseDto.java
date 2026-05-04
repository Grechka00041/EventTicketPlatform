package com.grechka.eventticketplatform.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListTicketTicketTypeResponseDto {
    private UUID uuid;
    private String name;
    private Double price;
}
