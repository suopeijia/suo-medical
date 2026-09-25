package com.suo.medical.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suo.medical.DTO.DepartmentDTO;
import com.suo.medical.common.response.Result;
import com.suo.medical.entity.Department;
import com.suo.medical.service.DepartmentService;
import com.suo.medical.tool.PageUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department")
@Tag(name = "科室管理", description = "科室管理")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    /**
     * 部门分页查询
     */
    @GetMapping("/page")
    @Operation(summary = "科室分页查询")
    public Result<PageUtil<Department>> getPageDepartment(@RequestParam(required = false) String name,
                                                          @RequestParam(defaultValue = "1") Long current,
                                                          @RequestParam(defaultValue = "10") Long size){
        return Result.success(departmentService.getPageDepartment(name, current, size));
    }

    /**
     * 部门新增
     */
    @PostMapping("/add")
    @Operation(summary = "科室新增")
    public Result<Department> addDepartment(@RequestParam String name){
        return Result.success(departmentService.adddepartmentDTO(name));
    }

    /**
     * 部门修改
     */
    @PutMapping("/update")
    @Operation(summary = "科室修改")
    public Result<Department> updateDepartment(@RequestBody DepartmentDTO departmentDTO){
        return Result.success(departmentService.updateDepartment(departmentDTO));
    }

    /**
     * 部门ID查询
     */
    @GetMapping("/getById/{id}")
    @Operation(summary = "科室ID查询")
    public Result<Department> getByIdDepartment(@PathVariable Long id){
        return Result.success(departmentService.getByIdDepartment(id));
    }

    /**
     * 部门删除
     */
    @DeleteMapping("/delete")
    @Operation(summary = "科室删除")
    public Result<Department> deleteDepartment(@RequestParam Long id){
        return Result.success(departmentService.deleteDepartment(id));
    }


}
