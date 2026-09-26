package com.suo.medical.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suo.medical.DTO.DoctorDTO;
import com.suo.medical.DTO.DoctorDTO;
import com.suo.medical.VO.DoctorVO;
import com.suo.medical.annotation.OperationLog;
import com.suo.medical.common.response.Result;
import com.suo.medical.entity.Doctor;
import com.suo.medical.entity.Doctor;
import com.suo.medical.service.DoctorService;
import com.suo.medical.service.DoctorService;
import com.suo.medical.tool.PageUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctor")
@RequiredArgsConstructor
@Tag(name = "医生管理",description = "医生CRUD")
public class DoctorController {

    private final DoctorService doctorService;

    /**
     * 医生分页查询
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
     * 医生新增
     */
    @PostMapping("/add")
    @Operation(summary = "医生新增")
    public Result<Doctor> addDoctor(@RequestBody DoctorDTO doctorDTO){
        return Result.success(doctorService.adddoctorDTO(doctorDTO));
    }

    /**
     * 医生修改
     */
    @PutMapping("/update")
    @Operation(summary = "医生修改")
    public Result<DoctorDTO> updateDoctor(@RequestBody DoctorDTO doctorDTO){
        return Result.success(doctorService.updateDoctor(doctorDTO));
    }

    /**
     * 医生ID查询
     */
    @GetMapping("/getById")
    @Operation(summary = "医生ID查询")
    public Result<DoctorDTO> getByIdDoctor(@RequestParam Long id){
        return Result.success(doctorService.getByIdDoctor(id));
    }

    /**
     * 医生删除
     */
    @DeleteMapping("/delete")
    @Operation(summary = "医生删除")
    public Result<Boolean> deleteDoctor(@RequestParam Long id){
        return Result.success(doctorService.deleteDoctor(id));
    }

    //手写sql联表查询
    @GetMapping("/getDoctorVOById")
    @Operation(summary = "医生VO查询")
    @OperationLog("医生VO分页查询")
    public Result<DoctorVO> getDoctorVOById(@RequestParam Long id){
        return Result.success(doctorService.getDoctorVOById(id));
    }
}
