package com.sharma.Quiz.service;

import com.sharma.Quiz.dto.LoginClass;
import com.sharma.Quiz.entity.User;
import com.sharma.Quiz.repo.LoginRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    LoginRepo loginRepo;
    public User saveUser(User user){
        String encodedPass = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPass);
        return loginRepo.save(user);
    }

    public User checkUser(LoginClass login){
        Optional<User> user = loginRepo.findByEmail(login.getEmail());
        if (user.isPresent()){
            User data = user.get();
            if(passwordEncoder.matches(login.getPassword(), data.getPassword())){
                return data;
            }else {
                return null;
            }
        }else{
            return null;
        }
    }
}
