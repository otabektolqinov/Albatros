package com.example.albartros.controller;

import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.dto.UserDto;
import com.example.albartros.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("user/")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @PostMapping("/{authId}")
    @Operation(summary = "Create a new user", description = "Creates a new user with the given details")
    @ApiResponse(responseCode = "201", description = "User created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    public HttpApiResponse<UserDto> createUser(@PathVariable(value = "authId") Long authId,
                                               @RequestBody @Valid UserDto dto) {
        log.info("user creating with auth id : {}", authId);
        return this.userService.createUser(authId, dto);
    }

    @GetMapping("/{id}")
    public HttpApiResponse<UserDto> getUserById(@PathVariable("id") Long id) {
        return this.userService.getUserById(id);
    }

    @GetMapping("/get-all")
    public HttpApiResponse<List<UserDto>> getAllUsers() {
        return this.userService.getAllUsers();
    }

   /* @GetMapping("/get-all-staff")
    public HttpApiResponse<List<UserDto>> getAllStaff() {
        return this.userService.getAllStaff();
    }*/

    @PutMapping("/{id}")
    public HttpApiResponse<UserDto> updateUserById(@PathVariable("id") Long id, @RequestBody UserDto dto) {
        return this.userService.updateUserById(id, dto);
    }

    @DeleteMapping("/{id}")
    public HttpApiResponse<String> deleteUserById(@PathVariable("id") Long id) {
        return this.userService.deleteUserById(id);
    }
}
