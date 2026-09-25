package com.suo.medical.service;

import com.suo.medical.common.response.Result;
import com.suo.medical.entity.Patient;

public interface PatientService {
    Patient getPatientById(Long id);

    Long addPatient(Patient patient);

    void updatePatient(Patient patient);

    Long deleteById(Long id);
}
