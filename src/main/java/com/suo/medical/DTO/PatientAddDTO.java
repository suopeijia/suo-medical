package com.suo.medical.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 患者新增入参 DTO。
 *
 * @author suo
 */
@Data
@Schema(description = "患者新增入参")
public class PatientAddDTO {

    /**
     * 字段校验三种注解：
     * 1.@NotNull
     * 2.@NotEmpty
     * 3.@NotBlank
     */

    /**
     * 患者姓名，不能为空
     */
    @Schema(description = "患者姓名")
    @NotBlank(message = "姓名不能为空")
    private String name;

    /**
     * 患者年龄，不能为空且不能小于 0
     */
    @Schema(description = "患者年龄")
    @NotNull(message = "年龄不能为空")
    @Min(value = 0, message = "年龄不能小于0")
    private Integer age;
}
