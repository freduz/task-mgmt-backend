package com.daniel.taskmanagerbackend.service;

import com.daniel.taskmanagerbackend.entity.User;
import com.daniel.taskmanagerbackend.payload.CurrentUserDto;
import com.daniel.taskmanagerbackend.payload.LoginRequestDto;
import com.daniel.taskmanagerbackend.payload.RegisterRequestDto;

public interface AuthenticationService {

    CurrentUserDto login(LoginRequestDto loginRequest);
    String register(RegisterRequestDto registerRequest);

    User getCurrentLoggedUser();
}
