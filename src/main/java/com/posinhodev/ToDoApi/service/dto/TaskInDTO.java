package com.posinhodev.ToDoApi.service.dto;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * DTO define que datos le vamos a pedir al usuario por debajo
 */
@Data
public class TaskInDTO {
    private String title;
    private String description;
    private LocalDateTime finalEstimateDate;

}
