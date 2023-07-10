package com.daniel.taskmanagerbackend.payload;

import com.daniel.taskmanagerbackend.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequestDto {

    @NotEmpty(message = "Title should not be empty")
    @Size(min = 5,message = "Title should not be less than 5 char length")
    private String title;

    @NotEmpty(message = "Description should not be empty")
    @Size(min = 10,message = "Description should not be less than 5 char length")
    private String description;

    @NotNull(message = "Due Date is required")
    private LocalDate dueDate;

    @NotEmpty(message = "Status should not be empty")
    private String status;

    @JsonIgnore
    private User user;
}
