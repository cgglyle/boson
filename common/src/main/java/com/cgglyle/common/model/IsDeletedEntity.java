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

package com.cgglyle.common.model;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 携带逻辑删除的类
 *
 * @author lyle
 * @since 2022/08/17
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class IsDeletedEntity extends BaseEntity{

    @Schema(description = "逻辑删除值('false'=>未删除，'ture'=>已删除)")
    @TableField("is_deleted")
    private Boolean isDeleted;
}
