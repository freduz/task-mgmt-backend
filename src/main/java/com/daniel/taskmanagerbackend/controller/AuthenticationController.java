package com.daniel.taskmanagerbackend.controller;

import com.daniel.taskmanagerbackend.entity.User;
import com.daniel.taskmanagerbackend.payload.*;
import com.daniel.taskmanagerbackend.service.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@Tag(
        name = "Endpoints for Authentication"
)
public class AuthenticationController {
    private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<RegisterResponse> signUp(@Valid @RequestBody RegisterRequestDto registerRequest){
      String response = authenticationService.register(registerRequest);
      RegisterResponse registerResponse = RegisterResponse.builder()
              .status("success")
              .message(response)
              .build();
      return new ResponseEntity<>(registerResponse,HttpStatus.CREATED);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<CurrentUserDto> signIn(@Valid @RequestBody LoginRequestDto loginRequest){
        CurrentUserDto currentUser = authenticationService.login(loginRequest);
        return new ResponseEntity<>(currentUser,HttpStatus.OK);
    }

    @GetMapping("/get-current-user")
    public ResponseEntity<UserDto> getCurrentUser(){
        User user = this.authenticationService.getCurrentLoggedUser();
        UserDto currentUser = UserDto.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
        return new ResponseEntity<>(currentUser,HttpStatus.OK);
    }
}
