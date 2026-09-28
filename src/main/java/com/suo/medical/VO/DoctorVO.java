package com.suo.medical.VO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 医生视图对象，在医生信息基础上附带关联的科室名称，用于联表查询结果返回。
 *
 * @author suo
 */
@Data
@Schema(description = "医生视图对象")
public class DoctorVO {
    /**
     * 医生主键 id
     */
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

    /**
     * 坐诊科室名称，来自关联的科室表
     */
    @Schema(description = "坐诊科室名称")
    private String departmentName;   // ← 比 Doctor 多这个,来自关联的科室表
}

