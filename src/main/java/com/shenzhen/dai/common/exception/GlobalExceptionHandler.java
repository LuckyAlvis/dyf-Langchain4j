package com.shenzhen.dai.common.exception;

import com.shenzhen.dai.common.response.ApiResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ApiResponse<Void> handleException(Exception e) {
        return ApiResponse.error(500, "服务器内部错误: " + e.getMessage());
    }
    
    // 可以添加更多特定异常的处理方法
} 