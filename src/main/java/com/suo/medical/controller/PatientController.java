package com.suo.medical.controller;

import com.suo.medical.common.response.Result;
import com.suo.medical.entity.Patient;
import com.suo.medical.service.PatientService;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
@Tag(name = "患者信息", description = "患者信息相关操作")
public class PatientController {

    private final PatientService patientService;


    @Operation(summary = "根据id获取患者信息")
    @GetMapping("/{id}")
    public Result<Patient> getPatientById(@PathVariable Long id){
        return Result.success(patientService.getPatientById(id));
    }

    @Operation(summary = "添加患者信息")
    @PostMapping("/addPatient")
    public Result<Long>  addPatient(@RequestBody Patient patient){
        patientService.addPatient(patient);
        return Result.success(patient.getId());
    }

    @Operation(summary = "更新患者信息")
    @PutMapping("/updatePatient/{id}")
    public Result<Patient> updatePatient(@PathVariable Long id ,@RequestBody Patient patient){
        patient.setId(id);
        patientService.updatePatient(patient);
        return Result.success(patient);
    }

    @Operation(summary = "根据id删除患者信息")
    @DeleteMapping("/deleteById/{id}")
    public Result<Long> deleteById(@PathVariable Long id) {
        return Result.success(patientService.deleteById(id));
    }
}
