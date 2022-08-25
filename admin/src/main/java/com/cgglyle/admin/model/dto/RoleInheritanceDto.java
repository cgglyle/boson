package com.cgglyle.admin.model.dto;

import lombok.Data;

/**
 * 角色继承关系存储模型
 * @author lyle
 * @since 2022/08/14
 */
@Data
public class RoleInheritanceDto {
    private Long roleId;
    private Long parentRoleId;
}
