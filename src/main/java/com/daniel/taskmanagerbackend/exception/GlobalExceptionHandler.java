package com.daniel.taskmanagerbackend.exception;


import com.daniel.taskmanagerbackend.payload.ErrorDetails;
import org.springframework.http.*;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    //handle specific exception
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
         ErrorDetails errorDetails = ErrorDetails.builder()
                 .message(exception.getMessage())
                 .timestamp(new Date())
                 .details(webRequest.getDescription(false))
                 .build();

         return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TaskApiException.class)
    public ResponseEntity<ErrorDetails> handleTaskApiResponse(TaskApiException exception,WebRequest webRequest){
        ErrorDetails errorDetails = ErrorDetails.builder()
                .message(exception.getMessage())
                .timestamp(new Date())
                .details(webRequest.getDescription(false))
                .build();

        return new ResponseEntity<>(errorDetails,exception.getHttpStatus());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorDetails> handleBadCredentials(BadCredentialsException exception,WebRequest request){
        ErrorDetails errorDetails = ErrorDetails.builder()
                .message("Please check your email or password")
                .timestamp(new Date())
                .details(request.getDescription(false))
                .build();
        return new ResponseEntity<>(errorDetails,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InsufficientAuthenticationException.class)
    public ResponseEntity<ErrorDetails> handleInsuffientAuthException(InsufficientAuthenticationException exception, WebRequest request){
        ErrorDetails errorDetails = ErrorDetails.builder()
                .message(exception.getMessage())
                .timestamp(new Date())
                .details(request.getDescription(false))
                .build();
        return new ResponseEntity<>(errorDetails,HttpStatus.UNAUTHORIZED);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName,message);
        });

        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }
}
