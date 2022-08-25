package com.cgglyle.admin.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author lyle
 * @date 2022/08/13
 */
@Schema(description = "用户角色关联添加请求")
@Data
public class UserRoleRelationSaveQuery {
    @NotNull(message = "用户ID不能为空")
    private Long userId;
    @NotNull(message = "角色ID不能为空")
    private Long roleId;
}
