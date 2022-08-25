package com.cgglyle.admin.query;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author lyle
 * @since 2022/08/21
 */
@Data
public class RolePermissionRelationSaveQuery {
    @NotNull(message = "角色id不能为空")
    private Long roleId;
    @NotNull(message = "权限id不能为空")
    private Long permissionId;
}
