package com.cgglyle.admin.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author lyle
 * @since 2022/08/21
 */
@Data
public class PermissionSaveQuery {
    @NotEmpty(message = "接口URL不能为空")
    @Schema(description = "接口URL")
    private String permissionUrl;
    @NotEmpty(message = "接口描述不能为空")
    @Schema(description = "接口描述")
    private String permissionDescription;
}
