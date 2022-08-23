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
