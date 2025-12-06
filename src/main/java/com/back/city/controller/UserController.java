package com.back.city.controller;

import com.back.city.dto.user.FindUserResponse;
import com.back.city.dto.user.UserProfileDTO;
import com.back.city.dto.auth.JwtAuthResponse;
import com.back.city.dto.auth.SingInRequest;
import com.back.city.dto.auth.SingUpRequest;
import com.back.city.services.AuthenticationService;
import com.back.city.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public JwtAuthResponse registerUser(@RequestBody SingUpRequest request){
        return authenticationService.singUp(request);

    }

    @PostMapping("/login")
    public JwtAuthResponse loginUser(@RequestBody SingInRequest request){
        authenticationService.notifyUserOnline(request);
        return authenticationService.singIn(request);
    }

    //TODO поменять на поиск по ассоциации чтобы выводились похожие варианты и возвращать список а не 1 элемент
    @GetMapping("/getUserByUserName/{userName}")
    public FindUserResponse findUserByUserName(@PathVariable String userName){
        return userService.findUserByUserName(userName);
    }
}

