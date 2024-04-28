package com.osos.taskmanager.service;

import com.osos.taskmanager.dto.TaskResponseDto;

import java.util.List;

public interface TaskService {
    List<TaskResponseDto> getAllTasks();
}
