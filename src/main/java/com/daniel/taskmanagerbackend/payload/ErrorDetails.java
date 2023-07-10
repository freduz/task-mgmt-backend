package com.daniel.taskmanagerbackend.payload;

import lombok.*;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;
}
