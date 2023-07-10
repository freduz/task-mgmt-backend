package com.daniel.taskmanagerbackend.utils;

import com.daniel.taskmanagerbackend.entity.Task;
import com.daniel.taskmanagerbackend.payload.TaskRequestDto;
import com.daniel.taskmanagerbackend.payload.TaskResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.stream.Collectors;


public class TaskMapper {

    private ModelMapper modelMapper;

    public TaskMapper(){
        this.modelMapper = new ModelMapper();
    }

    public Task mapToEntity(TaskRequestDto taskRequestDto){
//        return Task.builder()
//                .title(taskRequestDto.getTitle())
//                .description(taskRequestDto.getDescription())
//                .dueDate(taskRequestDto.getDueDate())
//                .status(taskRequestDto.getStatus())
//                .user()
//                .build();
        return this.modelMapper.map(taskRequestDto,Task.class);
    }

    public TaskResponseDto mapToDto(Task task){
       return this.modelMapper.map(task,TaskResponseDto.class);
    }

    public List<TaskResponseDto> mapToListOfTaskResponse(List<Task> tasks){

        return tasks.stream().map(task -> {
            return this.modelMapper.map(task,TaskResponseDto.class);
        }).toList();
    }
}
