package com.suo.medical.VO;

import lombok.Data;

@Data
public class DashboardVO {
    private Long patientCount;
    private Long doctorCount;
    private Long departmentCount;
    private Long medicineCount;
}
