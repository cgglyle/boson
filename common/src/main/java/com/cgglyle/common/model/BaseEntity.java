package com.cgglyle.common.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

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
    private LocalDate createTime;

    @Schema(description = "更新时间")
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private LocalDate updateTime;

    @Schema(description = "逻辑删除值('false'=>未删除，'ture'=>已删除)")
    @TableField("is_deleted")
    private Boolean isDeleted;

    @Schema(description = "状态值('0'=>异常，'1'=>正常)")
    @TableField("is_status")
    private Boolean isStatus;
}
