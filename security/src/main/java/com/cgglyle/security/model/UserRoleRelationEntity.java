package com.cgglyle.security.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cgglyle.common.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author lyle
 * @date 2022/08/13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("security_user_role_relation")
public class UserRoleRelationEntity extends BaseEntity {
    @NotNull(message = "用户ID不能为空")
    @Schema(description = "用户ID")
    private Long userId;
    @NotNull(message = "角色ID不能为空")
    @Schema(description = "角色ID")
    private Long roleId;
}
