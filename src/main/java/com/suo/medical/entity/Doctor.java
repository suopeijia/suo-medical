package com.suo.medical.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 医生实体，对应数据库医生表。
 *
 * @author suo
 */
@Data
@Schema(description = "医生实体")
public class Doctor {

    /**
     * 医生主键 id
     */
    @TableId(type = IdType.AUTO)
    @Schema(description = "医生主键id")
    private Long id;

    /**
     * 医生姓名
     */
    @Schema(description = "医生姓名")
    private String name;

    /**
     * 坐诊科室 id
     */
    @Schema(description = "坐诊科室id")
    private Long departmentId;

}
