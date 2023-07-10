package com.daniel.taskmanagerbackend.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
public class TaskApiException extends RuntimeException {
    private HttpStatus httpStatus;
    private String message;

    public TaskApiException(HttpStatus httpStatus,String message){
        super(message);
        this.httpStatus =httpStatus;
        this.message = message;
    }
}
