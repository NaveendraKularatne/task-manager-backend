package com.osos.taskmanager.service.impl;

import com.osos.taskmanager.dto.TaskRequestDto;
import com.osos.taskmanager.dto.TaskResponseDto;
import com.osos.taskmanager.entity.Task;
import com.osos.taskmanager.repository.TaskRepository;
import com.osos.taskmanager.service.TaskService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<TaskResponseDto> getAllTasks() {
        List<Task> taskList = taskRepository.findAll();
        List<TaskResponseDto> taskResponseDtoList = new ArrayList<>();
        taskList.forEach(task -> {
            TaskResponseDto taskResponseDto = new TaskResponseDto();
            BeanUtils.copyProperties(task, taskResponseDto);
            taskResponseDtoList.add(taskResponseDto);
        });
        return taskResponseDtoList;
    }

    @Override
    public TaskResponseDto addTask(TaskRequestDto taskRequestDto) {
        Task task = new Task();
        BeanUtils.copyProperties(taskRequestDto, task);
        task = taskRepository.save(task);

        TaskResponseDto taskResponseDto = new TaskResponseDto();
        BeanUtils.copyProperties(task, taskResponseDto);
        taskResponseDto.setDuedate(task.getDuedate());
        return taskResponseDto;
    }
}
