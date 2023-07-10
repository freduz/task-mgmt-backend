package com.daniel.taskmanagerbackend.service.impl;

import com.daniel.taskmanagerbackend.entity.User;
import com.daniel.taskmanagerbackend.exception.ResourceNotFoundException;
import com.daniel.taskmanagerbackend.exception.TaskApiException;
import com.daniel.taskmanagerbackend.payload.CurrentUserDto;
import com.daniel.taskmanagerbackend.payload.LoginRequestDto;
import com.daniel.taskmanagerbackend.payload.RegisterRequestDto;
import com.daniel.taskmanagerbackend.repository.UserRespository;
import com.daniel.taskmanagerbackend.security.JwtTokenProvider;
import com.daniel.taskmanagerbackend.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private AuthenticationManager authenticationManager;
    private UserRespository userRespository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;
    public AuthenticationServiceImpl(AuthenticationManager authenticationManager,
                                     UserRespository userRespository,
                                     PasswordEncoder passwordEncoder,
                                     JwtTokenProvider jwtTokenProvider){
        this.authenticationManager = authenticationManager;
        this.userRespository = userRespository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public CurrentUserDto login(LoginRequestDto loginRequest) {
       Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token =  jwtTokenProvider.generateToken(authentication);
        User user = this.getCurrentLoggedUser();

        return CurrentUserDto.builder()
                .accessToken(token)
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();

    }

    @Override
    public String register(RegisterRequestDto registerRequest) {
        if(userRespository.existsByEmail(registerRequest.getEmail())){
          throw new TaskApiException(HttpStatus.BAD_REQUEST,"User already exists with this email");
        }

        User user = User.builder()
                .email(registerRequest.getEmail())
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role("ROLE_USER")
                .build();
        userRespository.save(user);
        return "register success";
    }

    @Override
    public User getCurrentLoggedUser() {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return this.userRespository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User","email",email));
    }
}
