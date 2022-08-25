package com.cgglyle.admin.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cgglyle.common.model.IsDeletedEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * <p>
 * 
 * </p>
 *
 * @author lyle
 * @since 2022-08-17
 */
@Getter
@Setter
@TableName("security_user_passwd")
@Schema(name = "UserPasswdEntity", description = "$!{table.comment}")
public class UserPasswdEntity extends IsDeletedEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "用户信息id")
    @TableField("user_id")
    private Long userId;

    @Schema(description = "用户密码")
    @TableField("user_passwd")
    private String userPasswd;
    private Long expiredTime;
}
