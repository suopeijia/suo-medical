package com.suo.medical.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suo.medical.DTO.DoctorDTO;
import com.suo.medical.VO.DoctorVO;
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

/**
 * 医生服务实现类，实现医生的增删改查、分页查询及联表查询业务。
 *
 * @author suo
 */
@Service
@RequiredArgsConstructor
@Tag(name = "医生管理",description = "医生管理")
public class DoctorServiceImpl implements DoctorService {

    private final DoctorMapper doctorMapper;

    private final DepartmentMapper departmentMapper;

    /**
     * 新增医生：根据科室名称查出科室 id 后落库，科室不存在则抛业务异常。
     *
     * @param doctorDTO 医生入参
     * @return 新增后的医生信息
     */
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

    /**
     * 根据 id 查询医生，并附带其所属科室名称。
     *
     * @param id 医生主键 id
     * @return 医生信息
     */
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

    /**
     * 根据 id 删除医生。
     *
     * @param id 医生主键 id
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteDoctor(Long id) {
        return doctorMapper.deleteById(id) > 0;
    }

    /**
     * 分页查询医生，支持按姓名模糊过滤。
     *
     * @param name    医生姓名，模糊匹配，可为空
     * @param current 当前页码
     * @param size    每页大小
     * @return 分页医生信息
     */
    @Override
    public PageUtil<Doctor> getPageDoctor(String name, Long current, Long size) {
        LambdaQueryWrapper<Doctor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name != null && !name.isEmpty(),Doctor::getName, name);
        Page<Doctor> page = new Page<>(current,size);
        doctorMapper.selectPage(page,queryWrapper);
        return PageUtil.of(page);
    }

    /**
     * 修改医生：根据科室名称查出科室 id 后更新，科室不存在则抛业务异常。
     *
     * @param doctorDTO 医生入参
     * @return 修改后的医生信息
     */
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

    /**
     * 根据 id 联表查询医生及其科室信息。
     *
     * @param id 医生主键 id
     * @return 含科室名称的医生视图对象
     */
    @Override
    public DoctorVO getDoctorVOById(Long id) {
        return doctorMapper.selectDoctorWithDept(id);
    }
}
