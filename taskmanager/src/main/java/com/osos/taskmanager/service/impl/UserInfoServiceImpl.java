package com.osos.taskmanager.service.impl;

import com.osos.taskmanager.dto.UserInfoRequestDto;
import com.osos.taskmanager.dto.UserInfoResponseDto;
import com.osos.taskmanager.entity.UserInfo;
import com.osos.taskmanager.repository.UserInfoRepository;
import com.osos.taskmanager.service.UserInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    private final PasswordEncoder passwordEncoder;
    private final UserInfoRepository userInfoRepository;

    public UserInfoServiceImpl(PasswordEncoder passwordEncoder, UserInfoRepository userInfoRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userInfoRepository = userInfoRepository;
    }

    @Override
    public ResponseEntity<UserInfoResponseDto> addUser(UserInfoRequestDto userInfoRequestDto) {
        UserInfoResponseDto userInfoResponseDto = new UserInfoResponseDto();
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userInfoRequestDto, userInfo);
        userInfo.setPassword(passwordEncoder.encode(userInfoRequestDto.getPassword()));
        userInfo = userInfoRepository.save(userInfo);
        BeanUtils.copyProperties(userInfo, userInfoResponseDto);
        return ResponseEntity.ok().body(userInfoResponseDto);
    }

    @Override
    public UserInfoResponseDto login(UserInfoRequestDto userInfoRequestDto) {
        UserInfo foundUser = userInfoRepository.findByName(userInfoRequestDto.getName())
                .orElseThrow(() -> new RuntimeException("Unknown user"));

        if (passwordEncoder.matches(CharBuffer.wrap(userInfoRequestDto.getPassword()), foundUser.getPassword())) {
            UserInfoResponseDto userInfoResponseDto = new UserInfoResponseDto();
            BeanUtils.copyProperties(foundUser, userInfoResponseDto);
            return userInfoResponseDto;
        }
        throw new RuntimeException("Invalid password");
    }
}
