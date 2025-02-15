package com.example.albartros.controller;

import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.dto.UserDto;
import com.example.albartros.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "User created successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input- Validation Error"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Unauthorized - Access denied"
            )
    })
    public HttpApiResponse<UserDto> createUser(@PathVariable(value = "authId") Long authId,
                                               @RequestBody @Valid UserDto dto) {
        log.info("user creating with auth id : {}", authId);
        return this.userService.createUser(authId, dto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user", description = "Get user by their Id")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "User retrieved successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid filter"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found"
            )
    }
    )
    public HttpApiResponse<UserDto> getUserById(@PathVariable("id") Long id) {
        return this.userService.getUserById(id);
    }


    @GetMapping("/get-all")
    @Operation(summary = "Get all user list", description = "get all user")
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
    public HttpApiResponse<List<UserDto>> getAllUsers() {
        return this.userService.getAllUsers();
    }

   /* @GetMapping("/get-all-staff")
    public HttpApiResponse<List<UserDto>> getAllStaff() {
        return this.userService.getAllStaff();
    }*/

    @PutMapping("/{id}")
    @Operation(summary = "Update user ById", description = "Update User by their Id")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "User updated successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Users not found"
            )
    })
    public HttpApiResponse<UserDto> updateUserById(@PathVariable("id") Long id, @RequestBody UserDto dto) {
        return this.userService.updateUserById(id, dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user ById", description = "Delete User by their Id")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "User deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Users not found"
            )
    })
    public HttpApiResponse<String> deleteUserById(@PathVariable("id") Long id) {
        return this.userService.deleteUserById(id);
    }
}
