package com.daniel.taskmanagerbackend.payload;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CurrentUserDto {
    private String accessToken;
    private String firstName;
    private String lastName;
    private String email;
}
