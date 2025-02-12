package com.example.albartros.controller;

import com.example.albartros.dto.AuthUserDto;
import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.service.AuthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthUserController {
    private final AuthUserService authUserService;

    @PostMapping("/register")
    public HttpApiResponse<AuthUserDto> registerAuthUser(@RequestBody AuthUserDto dto) {
        return this.authUserService.registerAuthUser(dto);
    }

    @PostMapping("/login")
    public HttpApiResponse<AuthUserDto> login(@RequestBody AuthUserDto dto) {
        return this.authUserService.login(dto);
    }

    @GetMapping("/{id}")
    public HttpApiResponse<AuthUserDto> getAuthUserByIdUser(@PathVariable Long id) {
        return this.authUserService.getAuthUserById(id);
    }

    @GetMapping("/get-all")
    public HttpApiResponse<List<AuthUserDto>> getAllAuthUser() {
        return this.authUserService.getAllAuthUsers();
    }

    @PutMapping("/{id}")
    public HttpApiResponse<AuthUserDto> updateAuthUser(@PathVariable Long id, @RequestBody AuthUserDto dto) {
        return this.authUserService.updateAuthUserById(id, dto);
    }

    @DeleteMapping("/{id}")
    public HttpApiResponse<String> deleteAuthUserById(@PathVariable Long id) {
        return this.authUserService.deleteAuthUserById(id);
    }

}
