package com.example.albartros.service.impl;

import com.example.albartros.dto.AuthUserDto;
import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.exception.ContentNotFoundException;
import com.example.albartros.exception.DatabaseException;
import com.example.albartros.model.AuthUser;
import com.example.albartros.repository.AuthUserRepository;
import com.example.albartros.service.AuthUserService;
import com.example.albartros.service.mapper.AuthUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthUserServiceImpl implements AuthUserService {
    private final AuthUserRepository authUserRepository;
    private final AuthUserMapper authUserMapper;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Override
    public HttpApiResponse<AuthUserDto> registerAuthUser(AuthUserDto dto) {
        try {
            if (authUserRepository.findByUsernameAndDeletedAtIsNull(dto.getUsername()).isPresent()) {
                return HttpApiResponse.<AuthUserDto>builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .message("Username is already in use")
                        .build();
            }
            dto.setPassword(encoder.encode(dto.getPassword()));
            AuthUser authUser = this.authUserRepository.saveAndFlush(this.authUserMapper.toEntity(dto));

            return HttpApiResponse.<AuthUserDto>builder()
                    .status(HttpStatus.CREATED)
                    .message("Successfully registered")
                    .content(this.authUserMapper.toDto(authUser))
                    .build();
        } catch (Exception e) {
            throw new DatabaseException("Unable to register user");
        }

    }

    @Override
    public HttpApiResponse<AuthUserDto> login(AuthUserDto dto) {
        try {
            if (authUserRepository.findByUsernameAndDeletedAtIsNull(dto.getUsername()).isPresent()) {
                return HttpApiResponse.<AuthUserDto>builder()
                        .status(HttpStatus.OK)
                        .message("Successfully logged in")
                        .content(this.authUserMapper.toDto(authUserRepository.findByUsernameAndDeletedAtIsNull(dto.getUsername()).get()))
                        .build();
            }
            throw new ContentNotFoundException("User not found");
        } catch (Exception e) {
            throw new ContentNotFoundException("Unable to login");
        }

    }

    @Override
    public HttpApiResponse<AuthUserDto> getAuthUserById(Long id) {
        try {
            if (authUserRepository.existsByIdAndDeletedAtIsNull(id)) {
                Optional<AuthUser> authUser = authUserRepository.findById(id);
                return HttpApiResponse.<AuthUserDto>builder()
                        .status(HttpStatus.OK)
                        .message("OK")
                        .content(this.authUserMapper.toDto(authUser.orElse(null)))
                        .build();
            }
            throw new ContentNotFoundException("User not found");
        } catch (Exception e) {
            throw new ContentNotFoundException("Unable to get user by id");
        }

    }

    @Override
    public HttpApiResponse<List<AuthUserDto>> getAllAuthUsers() {
        try {
            List<AuthUser> authUserList = authUserRepository.findAllByDeletedAtIsNull();
            if (!authUserList.isEmpty()) {
                return HttpApiResponse.<List<AuthUserDto>>builder()
                        .status(HttpStatus.OK)
                        .message("OK")
                        .content(this.authUserMapper.toDtoList(authUserList))
                        .build();
            }
            throw new ContentNotFoundException("Users not found");
        } catch (Exception e) {
            throw new ContentNotFoundException("Unable to get users");
        }
    }

    @Override
    public HttpApiResponse<AuthUserDto> updateAuthUserById(Long id, AuthUserDto dto) {
        try {
            if (authUserRepository.existsByIdAndDeletedAtIsNull(id)) {
                Optional<AuthUser> authUser = authUserRepository.findById(id);
                if (authUser.isPresent()) {
                    AuthUser updateAuthUser = this.authUserMapper.updateAuthUser(authUser.get(), dto);
                    authUserRepository.saveAndFlush(updateAuthUser);
                    return HttpApiResponse.<AuthUserDto>builder()
                            .status(HttpStatus.OK)
                            .message("Successfully updated")
                            .content(this.authUserMapper.toDto(updateAuthUser))
                            .build();
                }
            }
            throw new ContentNotFoundException("User not found");
        } catch (Exception e) {
            throw new DatabaseException("Unable to update user");
        }
    }

    @Override
    public HttpApiResponse<String> deleteAuthUserById(Long id) {
        try {

            if (authUserRepository.existsByIdAndDeletedAtIsNull(id)) {
                Optional<AuthUser> authUser = authUserRepository.findById(id);

                if (authUser.isPresent()) {
                    authUser.get().setDeletedAt(LocalDateTime.now());

                    this.authUserRepository.saveAndFlush(authUser.get());

                    return HttpApiResponse.<String>builder()
                            .status(HttpStatus.OK)
                            .message("Successfully deleted")
                            .build();
                }
            }
            throw new ContentNotFoundException("User not found");
        } catch (Exception e) {
            throw new DatabaseException("Unable to delete user");
        }

    }
}
