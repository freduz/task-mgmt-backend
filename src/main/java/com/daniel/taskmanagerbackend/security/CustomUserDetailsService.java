package com.daniel.taskmanagerbackend.security;

import com.daniel.taskmanagerbackend.entity.User;
import com.daniel.taskmanagerbackend.repository.UserRespository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRespository userRespository;
    public CustomUserDetailsService(UserRespository userRespository){
        this.userRespository = userRespository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRespository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found with this email "+email));
        Set<GrantedAuthority> authorities = Set.of(new SimpleGrantedAuthority(user.getRole()));
        return  new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);
    }
}
