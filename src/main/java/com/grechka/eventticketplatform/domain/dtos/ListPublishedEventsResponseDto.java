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
public class ListPublishedEventsResponseDto {
    private UUID id;
    private String name;
    private String venue;
}
