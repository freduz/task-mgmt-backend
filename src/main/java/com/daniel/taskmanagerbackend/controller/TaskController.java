package com.daniel.taskmanagerbackend.controller;

import com.daniel.taskmanagerbackend.entity.Task;
import com.daniel.taskmanagerbackend.payload.TaskRequestDto;
import com.daniel.taskmanagerbackend.payload.TaskResponseDto;
import com.daniel.taskmanagerbackend.service.TaskService;
import com.daniel.taskmanagerbackend.utils.TaskMapper;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@Tag(
        name = "Endpoints for Task Resource"
)
public class TaskController {

    private TaskService taskService;
    private TaskMapper taskMapper;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
        this.taskMapper = new TaskMapper();
    }

    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PostMapping
    public ResponseEntity<TaskResponseDto> create(@Valid @RequestBody TaskRequestDto taskRequestDto){
        TaskResponseDto taskResponseDto = this.taskMapper.mapToDto(this.taskService.createTask(taskRequestDto));
        return new ResponseEntity<>(taskResponseDto, HttpStatus.CREATED);
    }

    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> getTask(@PathVariable("id") Long taskId){
        TaskResponseDto taskResponseDto = this.taskMapper.mapToDto(this.taskService.getTask(taskId));
        return new ResponseEntity<>(taskResponseDto,HttpStatus.OK);
    }

    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @GetMapping
    public ResponseEntity<List<TaskResponseDto>> getAllTask(){
       List<TaskResponseDto> tasks = this.taskMapper.mapToListOfTaskResponse(this.taskService.getAllTask());
       return new ResponseEntity<>(tasks,HttpStatus.OK);
    }

    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") Long taskId){
        this.taskService.deleteTask(taskId);
        return new ResponseEntity<>("Task deleted successfully",HttpStatus.NO_CONTENT);
    }

    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDto> updateTask(@PathVariable("id") Long taskId,@RequestBody TaskRequestDto taskRequestDto){
        TaskResponseDto taskResponseDto =this.taskMapper.mapToDto(this.taskService.updateTask(taskId,taskRequestDto));
        return new ResponseEntity<>(taskResponseDto,HttpStatus.OK);
    }
}
