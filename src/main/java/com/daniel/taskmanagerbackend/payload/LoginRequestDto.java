package com.daniel.taskmanagerbackend.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequestDto {

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Please provide the valid email")
    private String email;
    @NotEmpty(message = "Password should not be empty")
    private String password;
}
