package com.example.albartros.controller;

import com.example.albartros.dto.AuthUserDto;
import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.dto.UserDto;
import com.example.albartros.service.AuthUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthUserController {
    private final AuthUserService authUserService;

    @PostMapping("/register")
    @Operation(summary = "Register user", description = "Register user by Username and Password")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "user created successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Error while creating user"
            )
    })
    public HttpApiResponse<AuthUserDto> registerAuthUser(@RequestBody @Valid AuthUserDto dto) {
        return this.authUserService.registerAuthUser(dto);
    }

    @PostMapping("/login")
    @Operation(summary = "User Login", description = "User login by Username and Password")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "User successfully login"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authentication failed"
            )
    }
    )
    public HttpApiResponse<AuthUserDto> login(@RequestBody AuthUserDto dto) {
        return this.authUserService.login(dto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user", description = "Get User by id")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "User retrieved successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found"
            )
    })
    public HttpApiResponse<AuthUserDto> getAuthUserByIdUser(@PathVariable Long id) {
        return this.authUserService.getAuthUserById(id);
    }

    @GetMapping("/get-all")
    @Operation(summary = "Get userList", description = "Get all user")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Users retrieved successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Users not found"
            )
    })
    public HttpApiResponse<List<AuthUserDto>> getAllAuthUser() {
        return this.authUserService.getAllAuthUsers();
    }

    @GetMapping("/getAllStaff")
    public HttpApiResponse<List<UserDto>> getAllStaff() {
        return null;
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update user", description = "Update user by Id")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Users updated successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Users not found with given id"
            )
    })
    public HttpApiResponse<AuthUserDto> updateAuthUser(@PathVariable Long id, @RequestBody AuthUserDto dto) {
        return this.authUserService.updateAuthUserById(id, dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user", description = "Delete user by Id")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "User deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Users not found with given id"
            )
    })
    public HttpApiResponse<String> deleteAuthUserById(@PathVariable Long id) {
        return this.authUserService.deleteAuthUserById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("setRole/{id}")
    @Operation(summary = "Update role", description = "Update UserRole by Id")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "UserRole updated successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Users not found with given id"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authentication failed"
            )
    })
    public HttpApiResponse<AuthUserDto> setRole(@PathVariable Long id,
                                                @RequestBody AuthUserDto dto) {
        return this.authUserService.updateRoleById(id, dto);

    }

}
