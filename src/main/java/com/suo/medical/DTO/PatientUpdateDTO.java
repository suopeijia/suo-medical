package com.suo.medical.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 患者更新入参 DTO。
 *
 * @author suo
 */
@Data
@Schema(description = "患者更新入参")
public class PatientUpdateDTO {

    /**
     * 患者姓名
     */
    @Schema(description = "患者姓名")
    private String name;

    /**
     * 患者年龄，不能小于 0
     */
    @Schema(description = "患者年龄")
    @Min(value = 0, message = "年龄不能小于0")
    private Integer age;

}
