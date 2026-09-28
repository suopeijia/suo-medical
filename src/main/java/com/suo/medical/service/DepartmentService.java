package com.suo.medical.service;

import com.suo.medical.DTO.DepartmentDTO;
import com.suo.medical.entity.Department;
import com.suo.medical.tool.PageUtil;

/**
 * 科室服务接口，定义科室的增删改查及分页查询业务。
 *
 * @author suo
 */
public interface DepartmentService {

    /**
     * 新增科室。
     *
     * @param name 科室名称
     * @return 新增后的科室信息
     */
    Department adddepartmentDTO(String name);

    /**
     * 根据 id 查询科室。
     *
     * @param id 科室主键 id
     * @return 科室信息
     */
    Department getByIdDepartment(Long id);

    /**
     * 根据 id 删除科室。
     *
     * @param id 科室主键 id
     * @return 删除结果
     */
    Department deleteDepartment(Long id);

    /**
     * 分页查询科室，支持按名称模糊过滤。
     *
     * @param name    科室名称，模糊匹配，可为空
     * @param current 当前页码
     * @param size    每页大小
     * @return 分页科室信息
     */
    PageUtil<Department> getPageDepartment(String name, Long current, Long size);

    /**
     * 修改科室。
     *
     * @param departmentDTO 科室入参
     * @return 修改后的科室信息
     */
    Department updateDepartment(DepartmentDTO departmentDTO);
}
