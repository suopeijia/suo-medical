package com.suo.medical.controller;

import com.suo.medical.DTO.PatientAddDTO;
import com.suo.medical.DTO.PatientUpdateDTO;
import com.suo.medical.common.response.Result;
import com.suo.medical.entity.Patient;
import com.suo.medical.service.PatientService;
import com.suo.medical.tool.PageUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
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
@RequestMapping("/patients")
@RequiredArgsConstructor
@Tag(name = "患者管理", description = "患者信息相关操作")
@Validated
public class PatientController {

    private final PatientService patientService;


    /**
     * 根据 id 获取患者信息。
     *
     * @param id 患者主键 id
     * @return 患者信息
     */
    @Operation(summary = "根据id获取患者信息")
    @GetMapping("/{id}")
    public Result<Patient> getPatientById(@PathVariable Long id) {
        return Result.success(patientService.getPatientById(id));
    }

    /**
     * 添加患者信息。
     *
     * @param patientAddDTO 患者新增入参
     * @return 新增患者的主键 id
     */
    @Operation(summary = "添加患者信息")
    @PostMapping("/addPatient")
    public Result<Long> addPatient(@Valid @RequestBody PatientAddDTO patientAddDTO) {
        Patient patient = new Patient();
        patient.setAge(patientAddDTO.getAge());
        patient.setName(patientAddDTO.getName());
        return Result.success(patientService.addPatient(patient));
    }

    /**
     * 更新患者信息。
     *
     * @param id               患者主键 id
     * @param patientUpdateDTO 患者更新入参
     * @return 更新后的患者信息
     */
    @Operation(summary = "更新患者信息")
    @PutMapping("/updatePatient/{id}")
    public Result<Patient> updatePatient(@Min(value = 0, message = "ID不能小于0") @PathVariable Long id, @Valid @RequestBody PatientUpdateDTO patientUpdateDTO) {
        Patient patient = new Patient();
        patient.setId(id);
        patient.setAge(patientUpdateDTO.getAge());
        patient.setName(patientUpdateDTO.getName());
        patientService.updatePatient(patient);
        return Result.success(patient);
    }

    /**
     * 根据 id 删除患者信息。
     *
     * @param id 患者主键 id
     * @return 删除影响的记录数
     */
    @Operation(summary = "根据id删除患者信息")
    @DeleteMapping("/deleteById/{id}")
    public Result<Long> deleteById(@PathVariable Long id) {
        return Result.success(patientService.deleteById(id));
    }

    /**
     * 分页查询患者信息，支持按姓名、年龄区间过滤。
     *
     * @param name    患者姓名，模糊匹配，可为空
     * @param minAge  最小年龄，可为空
     * @param maxAge  最大年龄，可为空
     * @param current 当前页码，默认 1
     * @param size    每页大小，默认 10
     * @return 分页患者信息
     */
    @Operation(summary = "分页查询患者信息")
    @GetMapping("/page")
    public Result<PageUtil<Patient>> page(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer minAge,
            @RequestParam(required = false) Integer maxAge,
            @RequestParam(defaultValue = "1") Long current,
            @RequestParam(defaultValue = "10") Long size){
        return Result.success(patientService.getPagePatient(name, minAge, maxAge, current, size));
    }


}
