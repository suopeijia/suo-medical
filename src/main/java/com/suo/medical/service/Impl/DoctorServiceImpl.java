package com.suo.medical.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suo.medical.DTO.DoctorDTO;
import com.suo.medical.common.enums.ResultCode;
import com.suo.medical.common.exception.BusinessException;
import com.suo.medical.entity.Department;
import com.suo.medical.entity.Doctor;
import com.suo.medical.mapper.DepartmentMapper;
import com.suo.medical.mapper.DoctorMapper;
import com.suo.medical.service.DoctorService;
import com.suo.medical.tool.PageUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Tag(name = "医生管理",description = "医生管理")
public class DoctorServiceImpl implements DoctorService {

    private final DoctorMapper doctorMapper;
    
    private final DepartmentMapper departmentMapper;

    @Override
    public Doctor adddoctorDTO(DoctorDTO doctorDTO) {
        Doctor doctor = new Doctor();
        doctor.setName(doctorDTO.getName());
        Department department = departmentMapper.selectOne(
                new LambdaQueryWrapper<Department>()
                        .eq(Department::getName, doctorDTO.getDepartmentName())
        );
        if(department == null){throw new BusinessException(ResultCode.DEPARTMENT_NOT_EXIST);}
        doctor.setDepartmentId(department.getId());
        doctorMapper.insert(doctor);
        return doctor;
    }

    @Override
    public DoctorDTO getByIdDoctor(Long id) {
        Doctor doctor = doctorMapper.selectById(id);
        Department department = new Department();
        department = departmentMapper.selectById(doctor.getDepartmentId());
        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setName(doctor.getName());
        try{
            if(department != null){doctorDTO.setDepartmentName(department.getName());}
        }catch (BusinessException e){
            throw new BusinessException(ResultCode.DEPARTMENT_NOT_EXIST);
        }
        return doctorDTO;
    }

    @Override
    public Boolean deleteDoctor(Long id) {
        return doctorMapper.deleteById(id) > 0;
    }

    @Override
    public PageUtil<Doctor> getPageDoctor(String name, Long current, Long size) {
        LambdaQueryWrapper<Doctor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name != null && !name.isEmpty(),Doctor::getName, name);
        Page<Doctor> page = new Page<>(current,size);
        doctorMapper.selectPage(page,queryWrapper);
        return PageUtil.of(page);
    }

    @Override
    @Transactional
    public DoctorDTO updateDoctor(DoctorDTO doctorDTO) {
        Department department = new Department();
        department = departmentMapper.selectOne(
                new LambdaQueryWrapper<Department>()
                        .eq(Department::getName, doctorDTO.getDepartmentName())
        );
        if(department == null){throw new BusinessException(ResultCode.DEPARTMENT_NOT_EXIST);}
        Doctor doctor = new Doctor();
        doctor.setId(doctorDTO.getId());
        doctor.setName(doctorDTO.getName());
        doctor.setDepartmentId(department.getId());
        doctorMapper.updateById(doctor);
        return doctorDTO;
    }
}
