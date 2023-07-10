package com.daniel.taskmanagerbackend.service;

import com.daniel.taskmanagerbackend.entity.Task;
import com.daniel.taskmanagerbackend.payload.TaskRequestDto;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    Task createTask(TaskRequestDto taskRequestDto);
    Task getTask(Long id);
    List<Task> getAllTask();
    void deleteTask(Long id);
    Task updateTask(Long id,TaskRequestDto taskRequestDto);
}
