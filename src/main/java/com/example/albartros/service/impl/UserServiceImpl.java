package com.example.albartros.service.impl;

import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.dto.UserDto;
import com.example.albartros.enums.UserRole;
import com.example.albartros.exception.ContentNotFoundException;
import com.example.albartros.exception.DatabaseException;
import com.example.albartros.model.AuthUser;
import com.example.albartros.model.User;
import com.example.albartros.repository.AuthUserRepository;
import com.example.albartros.repository.UserRepository;
import com.example.albartros.service.UserService;
import com.example.albartros.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AuthUserRepository authUserRepository;
    private final UserMapper userMapper;

    @Override
    public HttpApiResponse<UserDto> createUser(Long authId, UserDto dto) {
        try {
            AuthUser authUser = authUserRepository.findByIdAndDeletedAtIsNull(authId)
                    .orElseThrow(() -> new ContentNotFoundException("Auth with id " + authId + " not found"));

            User entity = this.userMapper.toEntity(dto);
            entity.setAuthUser(authUser);

//            authUser.setUser(entity);
//            authUserRepository.save(authUser);

            this.userRepository.save(entity);

            return HttpApiResponse.<UserDto>builder()
                    .status(HttpStatus.CREATED)
                    .message("User created successfully")
                    .content(this.userMapper.toDto(entity))
                    .build();
        } catch (ContentNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new DatabaseException("Unable to create user due to internal error");
        }
    }


    @Override
    public HttpApiResponse<UserDto> getUserById(Long id) {
        try {
            if (userRepository.existsByIdAndDeletedAtIsNull(id)) {
                if (this.userRepository.findByIdAndDeletedAtIsNull(id).isPresent()) {
                    return HttpApiResponse.<UserDto>builder()
                            .status(HttpStatus.OK)
                            .message("OK")
                            .content(this.userMapper.toDto((userRepository.findByIdAndDeletedAtIsNull(id).get())))
                            .build();
                }
            }
            throw new ContentNotFoundException("User with id " + id + " not found");
        } catch (Exception e) {
            throw new ContentNotFoundException("User not found");
        }

    }

    @Override
    public HttpApiResponse<List<UserDto>> getAllUsers() {
        try {
            List<User> users = userRepository.findAllByDeletedAtIsNull();
            if (!users.isEmpty()) {
                return HttpApiResponse.<List<UserDto>>builder()
                        .status(HttpStatus.OK)
                        .message("OK")
                        .content(userMapper.toDtoList(users))
                        .build();
            }
            throw new ContentNotFoundException("UserList not found");
        } catch (Exception e) {
            throw new ContentNotFoundException("Users not found");
        }

    }


    @Override
    public HttpApiResponse<UserDto> updateUserById(Long id, UserDto dto) {
        try {
            if (userRepository.existsByIdAndDeletedAtIsNull(id)) {
                if (this.userRepository.findByIdAndDeletedAtIsNull(id).isPresent()) {

                    User user = userRepository.findByIdAndDeletedAtIsNull(id).get();

                    User updateUser = this.userMapper.updateUser(user, dto);

                    this.userRepository.save(updateUser);

                    return HttpApiResponse.<UserDto>builder()
                            .status(HttpStatus.OK)
                            .message("OK")
                            .content(this.userMapper.toDto(updateUser))
                            .build();
                }
            }
        } catch (Exception e) {
            throw new ContentNotFoundException("User not found");
        }
        return null;
    }

    @Override
    public HttpApiResponse<String> deleteUserById(Long id) {
        try {
            if (userRepository.existsByIdAndDeletedAtIsNull(id)) {
                if (this.userRepository.findByIdAndDeletedAtIsNull(id).isPresent()) {
                    User user = this.userRepository.findByIdAndDeletedAtIsNull(id).get();

                    user.getAuthUser().setDeletedAt(LocalDateTime.now());

                    authUserRepository.save(user.getAuthUser());

                    user.setDeletedAt(LocalDateTime.now());

                    this.userRepository.save(user);

                    return HttpApiResponse.<String>builder()
                            .status(HttpStatus.OK)
                            .message("User deleted successfully")
                            .build();
                }
            }
            throw new ContentNotFoundException("User with id " + id + " not found");
        } catch (Exception e) {
            throw new ContentNotFoundException("User not found");
        }

    }
}
