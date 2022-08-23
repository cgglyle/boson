package com.cgglyle.security.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cgglyle.common.model.IsDeletedEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

/**
 * @author lylecgg
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("security_user_info")
public class UserEntity extends IsDeletedEntity {
    @NotEmpty(message = "名字不能为空")
    private String nickname;
    @NotEmpty(message = "邮箱不能为空")
    private String email;
    @NotEmpty(message = "电话不能为空")
    private String phone;
    @Schema(description = "过期时间")
    private Long expiredTime;
}
