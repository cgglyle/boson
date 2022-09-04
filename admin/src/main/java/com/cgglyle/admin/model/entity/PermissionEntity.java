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
