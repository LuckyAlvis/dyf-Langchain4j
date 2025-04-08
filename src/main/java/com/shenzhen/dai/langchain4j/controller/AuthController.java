package com.shenzhen.dai.langchain4j.controller;

import com.shenzhen.dai.langchain4j.dto.req.LoginRequest;
import com.shenzhen.dai.langchain4j.dto.resp.ApiResponse;
import com.shenzhen.dai.langchain4j.dto.resp.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "认证接口", description = "用户认证相关接口")
public class AuthController {

    @PostMapping("/login")
    @Operation(summary = "用户登录", description = "使用用户名/邮箱和密码进行登录")
    public ApiResponse<LoginResponse> login(@RequestBody LoginRequest req) {
        // TODO: 实现登录逻辑
        LoginResponse response = new LoginResponse();
        response.setToken("jwt-token");
        response.setUserId(1L);
        response.setUsername(req.getUsername());
        response.setAvatar("https://example.com/avatar.jpg");
        return ApiResponse.success("登录成功", response);
    }

    @PostMapping("/logout")
    @Operation(summary = "用户登出", description = "退出登录")
    public ApiResponse<Void> logout() {
        // TODO: 实现登出逻辑
        return ApiResponse.success("登出成功", null);
    }
}
