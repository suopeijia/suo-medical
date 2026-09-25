package com.suo.medical.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PatientAddDTO {

    /**
     * 字段校验三种注解：
     * 1.@NotNull
     * 2.@NotEmpty
     * 3.@NotBlank
     */

    @NotBlank(message = "姓名不能为空")
    private String name;

    @NotNull(message = "年龄不能为空")
    @Min(value = 0, message = "年龄不能小于0")
    private Integer age;
}
