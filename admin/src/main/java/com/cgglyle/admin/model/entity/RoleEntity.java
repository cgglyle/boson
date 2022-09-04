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

package com.cgglyle.admin.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cgglyle.common.model.IsDeletedEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

/**
 * @author lyle
 * @date 2022/08/13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("security_role_info")
public class RoleEntity extends IsDeletedEntity {
    @NotEmpty(message = "角色代码不能为空")
    @Schema(description = "角色代码")
    private String roleCode;
    @NotEmpty(message = "角色名字不能为空")
    @Schema(description = "角色名字")
    private String roleName;
    @NotEmpty(message = "角色描述不能为空")
    @Schema(description = "角色id")
    private String roleDescription;
}
