package com.daniel.taskmanagerbackend.service.impl;

import com.daniel.taskmanagerbackend.entity.Task;
import com.daniel.taskmanagerbackend.exception.ResourceNotFoundException;
import com.daniel.taskmanagerbackend.payload.TaskRequestDto;
import com.daniel.taskmanagerbackend.repository.TaskRepository;
import com.daniel.taskmanagerbackend.service.AuthenticationService;
import com.daniel.taskmanagerbackend.service.TaskService;
import com.daniel.taskmanagerbackend.utils.TaskMapper;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;
    private TaskMapper taskMapper;
    private AuthenticationService authenticationService;

    public TaskServiceImpl(TaskRepository taskRepository,AuthenticationService authenticationService){
        this.taskRepository = taskRepository;
        this.authenticationService = authenticationService;
        taskMapper = new TaskMapper();
    }
    @Override
    public Task createTask(TaskRequestDto taskRequestDto) {
        taskRequestDto.setUser(authenticationService.getCurrentLoggedUser());
        return this.taskRepository.save(taskMapper.mapToEntity(taskRequestDto));
    }

    @Override
    public Task getTask(Long id) {
        return this.taskRepository.findByIdAndUser(id,authenticationService.getCurrentLoggedUser()).orElseThrow(() -> new ResourceNotFoundException("Task","ID",id.toString()));
    }

    @Override
    public List<Task> getAllTask() {
        return this.taskRepository.findAllByUser(authenticationService.getCurrentLoggedUser());
    }

    @Override
    public void deleteTask(Long id) {
        Task task = this.taskRepository.findByIdAndUser(id,authenticationService.getCurrentLoggedUser()).orElseThrow(() -> new ResourceNotFoundException("Task","ID",id.toString()));
        this.taskRepository.delete(task);
    }

    @Override
    public Task updateTask(Long id, TaskRequestDto taskRequestDto) {
        taskRequestDto.setUser(authenticationService.getCurrentLoggedUser());
        Task existingTask = this.taskRepository.findByIdAndUser(id,authenticationService.getCurrentLoggedUser()).orElseThrow(() -> new ResourceNotFoundException("Task","ID",id.toString()));
        BeanUtils.copyProperties(taskRequestDto,existingTask);
        return this.taskRepository.save(existingTask);
    }
}
