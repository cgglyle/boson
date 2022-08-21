package com.cgglyle.security.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cgglyle.common.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 接口权限表
 * </p>
 *
 * @author lyle
 * @since 2022-08-21
 */
@Getter
@Setter
@TableName("security_permission")
@Schema(name = "PermissionEntity", description = "$!{table.comment}")
public class PermissionEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema(description = "接口URL")
    @TableField("permission_url")
    private String permissionUrl;

    @Schema(description = "接口描述")
    @TableField("permission_description")
    private String permissionDescription;
}
