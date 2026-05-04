package com.grechka.eventticketplatform.domain.dtos;

import com.grechka.eventticketplatform.domain.enums.TicketValidationMethodEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketValidationRequestDto {
    private UUID id;
    private TicketValidationMethodEnum method;

}
