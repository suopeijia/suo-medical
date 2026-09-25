package com.suo.medical.service;

import com.suo.medical.DTO.DepartmentDTO;
import com.suo.medical.entity.Department;
import com.suo.medical.tool.PageUtil;

public interface DepartmentService {

    Department adddepartmentDTO(String name);

    Department getByIdDepartment(Long id);

    Department deleteDepartment(Long id);

    PageUtil<Department> getPageDepartment(String name, Long current, Long size);

    Department updateDepartment(DepartmentDTO departmentDTO);
}
