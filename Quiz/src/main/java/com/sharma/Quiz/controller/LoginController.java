package com.sharma.Quiz.controller;

import com.sharma.Quiz.dto.LoginClass;
import com.sharma.Quiz.entity.User;
import com.sharma.Quiz.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    LoginService loginService;
    @PostMapping("/register")
    public User addUser(@RequestBody User user){
        return loginService.saveUser(user);
    }

    @PostMapping("/cusLogin")
    public ResponseEntity<User> findUser(@RequestBody LoginClass login){
        User user = loginService.checkUser(login);
        if(user!=null){
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(401).body(null);
    }
}
