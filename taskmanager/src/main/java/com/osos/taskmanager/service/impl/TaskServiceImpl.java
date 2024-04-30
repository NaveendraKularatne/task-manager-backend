package com.osos.taskmanager.service.impl;

import com.osos.taskmanager.dto.TaskRequestDto;
import com.osos.taskmanager.dto.TaskResponseDto;
import com.osos.taskmanager.entity.Task;
import com.osos.taskmanager.repository.TaskRepository;
import com.osos.taskmanager.service.TaskService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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
        List<Task> taskList = taskRepository.findAllByOrderByIdAsc();
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

    //    Update a task by id
    @Override
    public TaskResponseDto updateTask(Long id, TaskRequestDto taskRequestDto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id " + id));

        if (taskRequestDto.getTitle() != null) {
            task.setTitle(taskRequestDto.getTitle());
        }
        if (taskRequestDto.getDescription() != null) {
            task.setDescription(taskRequestDto.getDescription());
        }
        if (taskRequestDto.getDuedate() != null) {
            task.setDuedate(taskRequestDto.getDuedate());
        }

        task = taskRepository.save(task);

        TaskResponseDto taskResponseDto = new TaskResponseDto();
        BeanUtils.copyProperties(task, taskResponseDto);
        return taskResponseDto;
    }

    //    Delete a task by id
    @Override
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new RuntimeException("Task not found with id " + id);
        }
        taskRepository.deleteById(id);
    }
}
