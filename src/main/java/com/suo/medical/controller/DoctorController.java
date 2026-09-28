package com.suo.medical.controller;

import com.suo.medical.DTO.DoctorDTO;
import com.suo.medical.VO.DoctorVO;
import com.suo.medical.annotation.OperationLog;
import com.suo.medical.common.response.Result;
import com.suo.medical.entity.Doctor;
import com.suo.medical.service.DoctorService;
import com.suo.medical.tool.PageUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 医生管理控制器，提供医生的分页查询、新增、修改、删除及联表查询接口。
 *
 * @author suo
 */
@RestController
@RequestMapping("/doctor")
@RequiredArgsConstructor
@Tag(name = "医生管理",description = "医生CRUD")
public class DoctorController {

    private final DoctorService doctorService;

    /**
     * 医生分页查询，支持按姓名模糊过滤。
     *
     * @param name    医生姓名，模糊匹配，可为空
     * @param current 当前页码，默认 1
     * @param size    每页大小，默认 10
     * @return 分页医生信息
     */
    @OperationLog("医生分页查询")
    @GetMapping("/page")
    @Operation(summary = "医生分页查询")
    public Result<PageUtil<Doctor>> getPageDoctor(@RequestParam(required = false) String name,
                                                          @RequestParam(defaultValue = "1") Long current,
                                                          @RequestParam(defaultValue = "10") Long size){
        return Result.success(doctorService.getPageDoctor(name, current, size));
    }

    /**
     * 医生新增。
     *
     * @param doctorDTO 医生入参
     * @return 新增后的医生信息
     */
    @PostMapping("/add")
    @Operation(summary = "医生新增")
    public Result<Doctor> addDoctor(@RequestBody DoctorDTO doctorDTO){
        return Result.success(doctorService.adddoctorDTO(doctorDTO));
    }

    /**
     * 医生修改。
     *
     * @param doctorDTO 医生入参
     * @return 修改后的医生信息
     */
    @PutMapping("/update")
    @Operation(summary = "医生修改")
    public Result<DoctorDTO> updateDoctor(@RequestBody DoctorDTO doctorDTO){
        return Result.success(doctorService.updateDoctor(doctorDTO));
    }

    /**
     * 根据 id 查询医生。
     *
     * @param id 医生主键 id
     * @return 医生信息
     */
    @GetMapping("/getById")
    @Operation(summary = "医生ID查询")
    public Result<DoctorDTO> getByIdDoctor(@RequestParam Long id){
        return Result.success(doctorService.getByIdDoctor(id));
    }

    /**
     * 医生删除。
     *
     * @param id 医生主键 id
     * @return 是否删除成功
     */
    @DeleteMapping("/delete")
    @Operation(summary = "医生删除")
    public Result<Boolean> deleteDoctor(@RequestParam Long id){
        return Result.success(doctorService.deleteDoctor(id));
    }

    /**
     * 根据 id 联表查询医生及其科室信息。
     *
     * @param id 医生主键 id
     * @return 含科室名称的医生视图对象
     */
    @GetMapping("/getDoctorVOById")
    @Operation(summary = "医生VO查询")
    @OperationLog("医生VO分页查询")
    public Result<DoctorVO> getDoctorVOById(@RequestParam Long id){
        return Result.success(doctorService.getDoctorVOById(id));
    }
}
