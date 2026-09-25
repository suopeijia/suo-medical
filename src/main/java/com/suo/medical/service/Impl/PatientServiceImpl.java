package com.suo.medical.service.Impl;

import com.suo.medical.common.enums.ResultCode;
import com.suo.medical.common.exception.BusinessException;
import com.suo.medical.common.response.Result;
import com.suo.medical.entity.Patient;
import com.suo.medical.mapper.PatientMapper;
import com.suo.medical.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientMapper patientMapper;

    @Override
    public Patient getPatientById(Long id) {
        Patient patient = patientMapper.selectById(id);
        if(patient == null){
            throw new BusinessException(ResultCode.PATIENT_NOT_FOUND);
        }
        return patient;
    }

    @Override
    public Long addPatient(Patient patient) {
        patientMapper.insert(patient);
        return patient.getId();
    }

    @Override
    public void updatePatient(Patient patient) {
        patientMapper.updateById(patient);
    }

    @Override
    public Long deleteById(Long id) {
        return (long) patientMapper.deleteById(id);
    }
}
