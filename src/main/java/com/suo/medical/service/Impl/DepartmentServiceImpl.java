package com.suo.medical.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suo.medical.DTO.DepartmentDTO;
import com.suo.medical.common.enums.ResultCode;
import com.suo.medical.common.exception.BusinessException;
import com.suo.medical.entity.Department;
import com.suo.medical.mapper.DepartmentMapper;
import com.suo.medical.service.DepartmentService;
import com.suo.medical.tool.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentMapper departmentMapper;

    @Override
    public Department adddepartmentDTO(String name) {
        Department department = new Department();
        Long count = departmentMapper.selectCount(
                new LambdaQueryWrapper<Department>().eq(
                        Department::getName, name
                )
        );
        if(count > 0){
            throw new BusinessException(ResultCode.DEPARTMENT_EXIST);
        }
        department.setName(name);
        departmentMapper.insert(department);
        return department;
    }

    @Override
    public Department getByIdDepartment(Long id) {
        Department department = new Department();
        department.setId(id);
        return departmentMapper.selectById(department.getId());
    }

    @Override
    public Department deleteDepartment(Long id) {
        departmentMapper.deleteById(id);
        return null;
    }

    @Override
    public PageUtil<Department> getPageDepartment(String name, Long current, Long size) {
        LambdaQueryWrapper<Department> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name != null && !name.isEmpty() ,Department::getName, name);
        Page<Department> page = new Page<>(current,size);
        departmentMapper.selectPage(page,queryWrapper);
        return PageUtil.of(page);
    }

    @Override
    public Department updateDepartment(DepartmentDTO departmentDTO) {
        Department department = new Department();
        department.setId(departmentDTO.getId());
        department.setName(departmentDTO.getName());
        departmentMapper.updateById(department);
//        return department;
        return departmentMapper.selectById(department.getId());
    }
}
