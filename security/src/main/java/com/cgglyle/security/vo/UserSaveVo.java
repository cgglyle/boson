package com.cgglyle.security.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Schema(description = "用户添加实体")
@Data
public class UserSaveVo {
    @Schema(description = "别名")
    @NotBlank(message = "别名不能为空")
    private String nickname;

    @Schema(description = "邮箱")
    @NotBlank(message = "邮箱不能为空")
    private String email;

    @Schema(description = "电话")
    @NotBlank(message = "电话不能为空")
    private String phone;
}
