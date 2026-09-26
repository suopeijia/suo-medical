package com.suo.medical.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suo.medical.annotation.OperationLog;
import com.suo.medical.common.response.Result;
import com.suo.medical.entity.Medicine;
import com.suo.medical.service.MedicineService;
import com.suo.medical.tool.PageUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "药品管理", description = "药品管理")
@RequestMapping("/medicine")
public class MedicineController {

    private final MedicineService medicineService;

    @PostMapping("/add")
    public Result<Boolean> addMedicine(@RequestBody Medicine medicine) {
        return Result.success(medicineService.save(medicine));
    }

    @OperationLog("删除药品")
    @DeleteMapping("/delete")
    public Result<Boolean> deleteMedicine(@RequestParam Long id,@RequestParam boolean flag) {
        return Result.success(medicineService.removeById(id, flag));
    }

    @PutMapping("/update")
    public Result<Boolean> updateMedicine(@RequestBody Medicine medicine) {
        return Result.success(medicineService.updateById(medicine));
    }

    @GetMapping("/get")
    public Result<Medicine> getMedicineById(@RequestParam Long id) {
        return Result.success(medicineService.getById(id));
    }

    @OperationLog("药品分页查询")
    @GetMapping("/getMedicines")
    public Result<PageUtil<Medicine>> getMedicines(@RequestParam Integer current, @RequestParam Integer size) {
        return Result.success(PageUtil.of(medicineService.page(new Page(current,size))));
    }
}
