package com.suo.medical.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PatientUpdateDTO {

    private String name;

    @Min(value = 0, message = "年龄不能小于0")
    private Integer age;

}
