package com.cgglyle.admin.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cgglyle.common.model.IsDeletedEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 角色继承关系表
 * </p>
 *
 * @author lyle
 * @since 2022-08-14
 */
@Getter
@Setter
@TableName("security_role_inheritance")
@Schema(name = "RoleInheritanceEntity", description = "$!{table.comment}")
public class RoleInheritanceEntity extends IsDeletedEntity {

    private static final long serialVersionUID = 1L;

    @Schema(description = "角色id")
    @TableField("role_id")
    private Long roleId;

    @Schema(description = "父角色ID")
    @TableField("role_parent_id")
    private Long roleParentId;
}
