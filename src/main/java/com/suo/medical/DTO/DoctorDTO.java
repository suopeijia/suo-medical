package com.suo.medical.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 医生数据传输对象，用于医生新增、修改、查询等接口的入参与出参。
 *
 * @author suo
 */
@Data
@Schema(description = "医生数据传输对象")
public class DoctorDTO {
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
     * 坐诊科室名称
     */
    @Schema(description = "坐诊科室名称")
    private String departmentName;
}
