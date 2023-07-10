package com.daniel.taskmanagerbackend.payload;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
}
