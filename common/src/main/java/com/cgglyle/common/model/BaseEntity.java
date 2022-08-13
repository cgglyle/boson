package com.cgglyle.common.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 顶级实体类
 *
 * @author lyle
 */
@Data
@Schema(description = "顶级实体")
public abstract class BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键ID")
    @TableId(type = IdType.AUTO)
    private Long id;

    @Schema(description = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @Schema(description = "创建用户ID")
    @TableField(value = "create_user_id", fill = FieldFill.INSERT)
    private Long createUserId;

    @Schema(description = "更新用户ID")
    @TableField(value = "update_user_id", fill = FieldFill.INSERT_UPDATE)
    private Long updateUserId;

    @Schema(description = "逻辑删除值('false'=>未删除，'ture'=>已删除)")
    @TableField("is_deleted")
    private Boolean isDeleted;

    @Schema(description = "状态值('false'=>正常，'ture'=>异常)")
    @TableField("is_status")
    private Boolean isStatus;
}
