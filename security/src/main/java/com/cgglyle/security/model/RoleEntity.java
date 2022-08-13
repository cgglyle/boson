package com.cgglyle.security.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cgglyle.common.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

/**
 * @author lyle
 * @date 2022/08/13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("security_role_info")
public class RoleEntity extends BaseEntity {
    @NotEmpty(message = "角色代码不能为空")
    @Schema(description = "角色代码")
    private String roleCode;
    @NotEmpty(message = "角色名字不能为空")
    @Schema(description = "角色名字")
    private String roleName;
    @NotEmpty(message = "角色描述不能为空")
    @Schema(description = "角色id")
    private String roleDescription;
}
