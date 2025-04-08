package com.shenzhen.dai.langchain4j.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "登录请求")
public class LoginRequest {
    @Schema(description = "用户名/邮箱", required = true)
    private String username;

    @Schema(description = "密码", required = true)
    private String password;

    @Schema(description = "是否记住我")
    private Boolean remember;
}
