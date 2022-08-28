package com.cgglyle.admin.query;

import javax.validation.constraints.NotBlank;

/**
 * @author lyle
 * @since 2022/08/28
 */
public record InitAdminSaveQuery(@NotBlank(message = "用户名不能为空") String nickname,
                                 @NotBlank(message = "电子邮箱不能为空") String email,
                                 @NotBlank(message = "密码不能为空") String password) {
}
