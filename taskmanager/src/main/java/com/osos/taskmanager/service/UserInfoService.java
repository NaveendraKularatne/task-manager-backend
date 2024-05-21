package com.osos.taskmanager.service;

import com.osos.taskmanager.dto.UserInfoRequestDto;
import com.osos.taskmanager.dto.UserInfoResponseDto;
import com.osos.taskmanager.exception.InvalidPasswordException;
import com.osos.taskmanager.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;

public interface UserInfoService {
    ResponseEntity<UserInfoResponseDto> addUser(UserInfoRequestDto userInfoRequestDto);

    UserInfoResponseDto login(UserInfoRequestDto userInfoRequestDto) throws UserNotFoundException, InvalidPasswordException;
}
