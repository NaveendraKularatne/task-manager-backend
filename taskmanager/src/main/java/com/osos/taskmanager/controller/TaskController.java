package com.osos.taskmanager.controller;

import com.osos.taskmanager.dto.TaskResponseDto;
import com.osos.taskmanager.entity.Task;
import com.osos.taskmanager.service.TaskService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<TaskResponseDto> getAllTasks() {
        return this.taskService.getAllTasks();
    }
}
