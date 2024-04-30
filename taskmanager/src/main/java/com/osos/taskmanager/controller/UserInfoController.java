package com.osos.taskmanager.controller;

import com.osos.taskmanager.dto.UserInfoRequestDto;
import com.osos.taskmanager.dto.UserInfoResponseDto;
import com.osos.taskmanager.service.UserInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserInfoController {

    private final UserInfoService userInfoService;

    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @PostMapping
    public ResponseEntity<UserInfoResponseDto> addUser(@RequestBody UserInfoRequestDto userInfoRequestDto) {
        return userInfoService.addUser(userInfoRequestDto);
    }
}
