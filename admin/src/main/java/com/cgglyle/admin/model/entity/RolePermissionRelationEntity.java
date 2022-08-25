package com.cgglyle.admin.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cgglyle.common.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 角色权限关联表
 * </p>
 *
 * @author lyle
 * @since 2022-08-21
 */
@Getter
@Setter
@TableName("security_role_permission_relation")
@Schema(name = "RolePermissionRelationEntity", description = "$!{table.comment}")
public class RolePermissionRelationEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField("role_id")
    private Long roleId;

    @TableField("permission_id")
    private Long permissionId;
}
