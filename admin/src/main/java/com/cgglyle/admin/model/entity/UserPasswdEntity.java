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
import com.cgglyle.common.model.IsDeletedEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * <p>
 * 
 * </p>
 *
 * @author lyle
 * @since 2022-08-17
 */
@Getter
@Setter
@TableName("security_user_passwd")
@Schema(name = "UserPasswdEntity", description = "$!{table.comment}")
public class UserPasswdEntity extends IsDeletedEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "用户信息id")
    @TableField("user_id")
    private Long userId;

    @Schema(description = "用户密码")
    @TableField("user_passwd")
    private String userPasswd;
    private Long expiredTime;
}
