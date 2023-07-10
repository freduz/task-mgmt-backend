package com.daniel.taskmanagerbackend.payload;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class RegisterResponse {
    private String message;
    private String status;
}
