package com.example.albartros.service;

import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    HttpApiResponse<UserDto> createUser(Long authId, UserDto dto);

    HttpApiResponse<UserDto> getUserById(Long id);

    HttpApiResponse<List<UserDto>> getAllUsers();

    HttpApiResponse<UserDto> updateUserById(Long id, UserDto dto);

    HttpApiResponse<String> deleteUserById(Long id);

}
