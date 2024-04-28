package com.osos.taskmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponseDto {
    private Long id;
    private String title;
    private String description;
    private Date date;
}
