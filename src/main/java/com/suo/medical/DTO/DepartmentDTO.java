package com.suo.medical.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 科室数据传输对象，用于科室修改等接口的入参。
 *
 * @author suo
 */
@Data
@Schema(description = "科室数据传输对象")
public class DepartmentDTO {
    /**
     * 科室主键 id
     */
    @Schema(description = "科室主键id")
    private Long id;

    /**
     * 科室名称
     */
    @Schema(description = "科室名称")
    private String name;
}
