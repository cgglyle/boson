package com.cgglyle.admin.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author lyle
 * @since 2022/08/14
 */
@Data
public class RoleInheritanceSaveQuery {

    @NotNull(message = "角色id不能为空")
    @Schema(description = "角色id")
    private Long roleId;

    @NotNull(message = "父角色ID不能为空")
    @Schema(description = "父角色ID")
    private Long roleParentId;

}
