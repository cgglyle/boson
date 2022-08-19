package com.cgglyle.security.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author lyle
 * @since 2022/08/18
 */
@Data
public class UserPasswdUpdateQuery {
    @NotNull(message = "用户不能为空")
    @Schema(description = "用户id")
    private Long userId;
    @NotBlank(message = "用户不能为空")
    @Schema(description = "老密码")
    private String oldPasswd;
    @NotBlank(message = "用户不能为空")
    @Schema(description = "新密码")
    private String newPasswd;
}
