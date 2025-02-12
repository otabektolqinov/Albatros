package com.example.albartros.service;

import com.example.albartros.dto.AuthUserDto;
import com.example.albartros.dto.HttpApiResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthUserService {
    HttpApiResponse<AuthUserDto> registerAuthUser(AuthUserDto dto);

    HttpApiResponse<AuthUserDto> login(AuthUserDto dto);

    HttpApiResponse<AuthUserDto> getAuthUserById(Long id);

    HttpApiResponse<AuthUserDto> updateAuthUserById(Long id, AuthUserDto dto);

    HttpApiResponse<String> deleteAuthUserById(Long id);

    HttpApiResponse<List<AuthUserDto>> getAllAuthUsers();
}
