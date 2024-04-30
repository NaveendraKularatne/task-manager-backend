package com.osos.taskmanager.service;

import com.osos.taskmanager.dto.TaskResponseDto;
import com.osos.taskmanager.dto.UserInfoRequestDto;
import com.osos.taskmanager.dto.UserInfoResponseDto;
import org.springframework.http.ResponseEntity;

public interface UserInfoService {
    ResponseEntity<UserInfoResponseDto> addUser(UserInfoRequestDto userInfoRequestDto);

    UserInfoResponseDto login(UserInfoRequestDto userInfoRequestDto);
}
