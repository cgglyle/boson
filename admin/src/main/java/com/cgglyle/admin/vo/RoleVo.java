/*
 * Copyright 2022 Cgglyle
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cgglyle.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author lyle
 * @since 2022/08/14
 */
@Data
public class RoleVo {
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
    @Schema(description = "角色代码")
    private String roleCode;
    @Schema(description = "角色名字")
    private String roleName;
    @Schema(description = "角色id")
    private String roleDescription;
}
