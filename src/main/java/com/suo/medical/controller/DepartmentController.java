package com.suo.medical.controller;

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

/**
 * 科室管理控制器，提供科室的分页查询、新增、修改、删除接口。
 *
 * @author suo
 */
@RestController
@RequestMapping("/department")
@Tag(name = "科室管理", description = "科室管理")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    /**
     * 科室分页查询，支持按名称模糊过滤。
     *
     * @param name    科室名称，模糊匹配，可为空
     * @param current 当前页码，默认 1
     * @param size    每页大小，默认 10
     * @return 分页科室信息
     */
    @GetMapping("/page")
    @Operation(summary = "科室分页查询")
    public Result<PageUtil<Department>> getPageDepartment(@RequestParam(required = false) String name,
                                                          @RequestParam(defaultValue = "1") Long current,
                                                          @RequestParam(defaultValue = "10") Long size){
        return Result.success(departmentService.getPageDepartment(name, current, size));
    }

    /**
     * 科室新增。
     *
     * @param name 科室名称
     * @return 新增后的科室信息
     */
    @PostMapping("/add")
    @Operation(summary = "科室新增")
    public Result<Department> addDepartment(@RequestParam String name){
        return Result.success(departmentService.adddepartmentDTO(name));
    }

    /**
     * 科室修改。
     *
     * @param departmentDTO 科室入参
     * @return 修改后的科室信息
     */
    @PutMapping("/update")
    @Operation(summary = "科室修改")
    public Result<Department> updateDepartment(@RequestBody DepartmentDTO departmentDTO){
        return Result.success(departmentService.updateDepartment(departmentDTO));
    }

    /**
     * 根据 id 查询科室。
     *
     * @param id 科室主键 id
     * @return 科室信息
     */
    @GetMapping("/getById/{id}")
    @Operation(summary = "科室ID查询")
    public Result<Department> getByIdDepartment(@PathVariable Long id){
        return Result.success(departmentService.getByIdDepartment(id));
    }

    /**
     * 科室删除。
     *
     * @param id 科室主键 id
     * @return 删除结果
     */
    @DeleteMapping("/delete")
    @Operation(summary = "科室删除")
    public Result<Department> deleteDepartment(@RequestParam Long id){
        return Result.success(departmentService.deleteDepartment(id));
    }


}
