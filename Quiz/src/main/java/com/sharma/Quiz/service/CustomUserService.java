package com.sharma.Quiz.service;

import com.sharma.Quiz.entity.User;
import com.sharma.Quiz.repo.LoginRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserService implements UserDetailsService {
    @Autowired
    LoginRepo loginRepo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> opt = loginRepo.findByEmail(email);
        if(opt.isEmpty()){
            throw new RuntimeException("User not found");
        }
        User user = opt.get();
        return org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}
