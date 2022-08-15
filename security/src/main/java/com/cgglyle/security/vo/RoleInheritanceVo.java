package com.cgglyle.security.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author lyle
 * @since 2022/08/14
 */
@Data
public class RoleInheritanceVo {
    @Schema(description = "主键ID")
    private Long id;
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
    @Schema(description = "创建用户ID")
    private Long createUserId;
    @Schema(description = "更新用户ID")
    private Long updateUserId;
    @Schema(description = "逻辑删除值('false'=>未删除，'ture'=>已删除)")
    private Boolean isDeleted;
    @Schema(description = "状态值('false'=>正常，'ture'=>异常)")
    private Boolean isStatus;
    @Schema(description = "是否是内置角色('false'=>不是，'ture'=>是)")
    private Boolean isBuiltIn;

    @Schema(description = "角色id")
    private Long roleId;
    @Schema(description = "父角色ID")
    private Long roleParentId;
}
