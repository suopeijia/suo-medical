package com.suo.medical.VO;

import lombok.Data;

@Data
public class DoctorVO {
    private Long id;
    private String name;
    private Long departmentId;
    private String departmentName;   // ← 比 Doctor 多这个,来自关联的科室表
}

