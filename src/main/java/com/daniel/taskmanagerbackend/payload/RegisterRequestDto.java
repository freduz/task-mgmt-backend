package com.daniel.taskmanagerbackend.payload;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class RegisterRequestDto {
    private String email;
    private String firstName;
    private String lastName;
    private String password;

}
