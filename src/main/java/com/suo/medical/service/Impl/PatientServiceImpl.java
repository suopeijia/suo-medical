package com.suo.medical.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suo.medical.common.enums.ResultCode;
import com.suo.medical.common.exception.BusinessException;
import com.suo.medical.common.response.Result;
import com.suo.medical.entity.Patient;
import com.suo.medical.mapper.PatientMapper;
import com.suo.medical.service.PatientService;
import com.suo.medical.tool.PageUtil;
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

    //分页查询

    @Override
    public PageUtil<Patient> getPagePatient(String name, Integer minAge, Integer maxAge, Long current, Long size) {
        LambdaQueryWrapper<Patient> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name != null,Patient::getName,name);
        queryWrapper.between(minAge != null && maxAge != null,Patient::getAge,minAge,maxAge);
        Page<Patient> page = new Page<>(current,size);
        patientMapper.selectPage(page,queryWrapper);
        return PageUtil.of(page);
    }
}
