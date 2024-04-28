package com.osos.taskmanager.controller;

import com.osos.taskmanager.dto.TaskRequestDto;
import com.osos.taskmanager.dto.TaskResponseDto;
import com.osos.taskmanager.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/addTask")
    public ResponseEntity<TaskResponseDto> addTask(@RequestBody TaskRequestDto taskRequestDto) {
        TaskResponseDto createdTask = taskService.addTask(taskRequestDto);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }
}
