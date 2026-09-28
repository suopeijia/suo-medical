package com.suo.medical.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 患者实体，对应数据库患者表。
 *
 * @author suo
 */
@Data
@Schema(description = "患者实体")
public class Patient {

    /**
     * 患者主键 id
     */
    @TableId(type = IdType.AUTO)
    @Schema(description = "患者主键id")
    private Long id;

    /**
     * 患者姓名
     */
    @Schema(description = "患者姓名")
    private String name;

    /**
     * 患者年龄
     */
    @Schema(description = "患者年龄")
    private Integer age;

    /**
     * 所属科室 id
     */
    @Schema(description = "所属科室id")
    private Long departmentId;
}
