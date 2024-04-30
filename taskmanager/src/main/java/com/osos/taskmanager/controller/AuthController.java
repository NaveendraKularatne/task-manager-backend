package com.osos.taskmanager.controller;

import com.osos.taskmanager.config.UserAuthProvider;
import com.osos.taskmanager.dto.UserInfoRequestDto;
import com.osos.taskmanager.dto.UserInfoResponseDto;
import com.osos.taskmanager.service.UserInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthController {

    private final UserInfoService userInfoService;
    private final UserAuthProvider userAuthProvider;

    public AuthController(UserInfoService userInfoService, UserAuthProvider userAuthProvider) {
        this.userInfoService = userInfoService;
        this.userAuthProvider = userAuthProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<UserInfoResponseDto> login(@RequestBody UserInfoRequestDto userInfoRequestDto) {
        UserInfoResponseDto userInfoResponseDto = this.userInfoService.login(userInfoRequestDto);
        userInfoResponseDto.setToken(this.userAuthProvider.createToken(userInfoRequestDto));
        return ResponseEntity.ok().body(userInfoResponseDto);
    }
}
