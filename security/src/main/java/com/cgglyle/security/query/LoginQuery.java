package com.cgglyle.security.query;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author lyle
 * @since 2022/08/19
 */
@Data
public class LoginQuery {
    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
}
