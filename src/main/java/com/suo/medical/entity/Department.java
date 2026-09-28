package com.suo.medical.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 科室实体，对应数据库科室表。
 *
 * @author suo
 */
@Data
@Schema(description = "科室实体")
public class Department {

    /**
     * 科室主键 id
     */
    @TableId(type = IdType.AUTO)
    @Schema(description = "科室主键id")
    private Long id;

    /**
     * 科室名称
     */
    @Schema(description = "科室名称")
    private String name;
}
