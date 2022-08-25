package com.cgglyle.admin.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author lyle
 * @since 2022/08/17
 */
@Data
public class UserPasswdSaveQuery {
    @Schema(description = "用户信息id")
    @NotNull(message = "用户不能为空")
    private Long userId;

    @Schema(description = "用户密码")
    @NotBlank(message = "密码不能为空")
    private String userPasswd;
}
