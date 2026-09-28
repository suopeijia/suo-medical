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

    /**
     * 根据 id 获取患者信息，不存在则抛出业务异常。
     *
     * @param id 患者主键 id
     * @return 患者信息
     */
    @Override
    public Patient getPatientById(Long id) {
        Patient patient = patientMapper.selectById(id);
        if(patient == null){
            throw new BusinessException(ResultCode.PATIENT_NOT_FOUND);
        }
        return patient;
    }

    /**
     * 添加患者。
     *
     * @param patient 患者信息
     * @return 新增患者的主键 id
     */
    @Override
    public Long addPatient(Patient patient) {
        patientMapper.insert(patient);
        return patient.getId();
    }

    /**
     * 更新患者信息。
     *
     * @param patient 患者信息
     */
    @Override
    public void updatePatient(Patient patient) {
        patientMapper.updateById(patient);
    }

    /**
     * 根据 id 删除患者。
     *
     * @param id 患者主键 id
     * @return 删除影响的记录数
     */
    @Override
    public Long deleteById(Long id) {
        return (long) patientMapper.deleteById(id);
    }

    /**
     * 分页查询患者，支持按姓名、年龄区间过滤。
     *
     * @param name    患者姓名，模糊匹配，可为空
     * @param minAge  最小年龄，可为空
     * @param maxAge  最大年龄，可为空
     * @param current 当前页码
     * @param size    每页大小
     * @return 分页患者信息
     */
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
