package com.suo.medical.service;

import com.suo.medical.common.response.Result;
import com.suo.medical.entity.Patient;
import com.suo.medical.tool.PageUtil;
import org.springframework.web.bind.annotation.RequestParam;

public interface PatientService {
    Patient getPatientById(Long id);

    Long addPatient(Patient patient);

    void updatePatient(Patient patient);

    Long deleteById(Long id);

    PageUtil<Patient> getPagePatient( String name, Integer minAge, Integer maxAge, Long current, Long size);
}
