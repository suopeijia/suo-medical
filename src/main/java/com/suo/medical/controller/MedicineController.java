package com.suo.medical.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suo.medical.annotation.OperationLog;
import com.suo.medical.common.response.Result;
import com.suo.medical.entity.Medicine;
import com.suo.medical.service.MedicineService;
import com.suo.medical.tool.PageUtil;
import io.swagger.v3.oas.annotations.Operation;
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

/**
 * 药品管理控制器，提供药品的增删改查、缓存查询及库存扣减接口。
 *
 * @author suo
 */
@RestController
@RequiredArgsConstructor
@Tag(name = "药品管理", description = "药品管理")
@RequestMapping("/medicine")
public class MedicineController {

    private final MedicineService medicineService;

    /**
     * 新增药品。
     *
     * @param medicine 药品信息
     * @return 是否新增成功
     */
    @Operation(summary = "新增药品")
    @PostMapping("/add")
    public Result<Boolean> addMedicine(@RequestBody Medicine medicine) {
        return Result.success(medicineService.save(medicine));
    }

    /**
     * 根据 id 删除药品。
     *
     * @param id   药品主键 id
     * @param flag 删除标志
     * @return 是否删除成功
     */
    @Operation(summary = "删除药品")
    @OperationLog("删除药品")
    @DeleteMapping("/delete")
    public Result<Boolean> deleteMedicine(@RequestParam Long id, @RequestParam boolean flag) {
        return Result.success(medicineService.removeById(id, flag));
    }

    /**
     * 更新药品信息。
     *
     * @param medicine 药品信息
     * @return 更新后的药品信息
     */
    @Operation(summary = "更新药品")
    @PutMapping("/update")
    public Result<Medicine> updateMedicine(@RequestBody Medicine medicine) {
        return Result.success(medicineService.updateByIdBySelf(medicine));
    }

    /**
     * 根据 id 查询药品。
     *
     * @param id 药品主键 id
     * @return 药品信息
     */
    @Operation(summary = "根据id查询药品")
    @GetMapping("/get")
    public Result<Medicine> getMedicineById(@RequestParam Long id) {
        return Result.success(medicineService.getById(id));
    }

    /**
     * 分页查询药品。
     *
     * @param current 当前页码
     * @param size    每页大小
     * @return 分页药品信息
     */
    @Operation(summary = "药品分页查询")
    @OperationLog("药品分页查询")
    @GetMapping("/getMedicines")
    public Result<PageUtil<Medicine>> getMedicines(@RequestParam Integer current, @RequestParam Integer size) {
        return Result.success(PageUtil.of(medicineService.page(new Page(current, size))));
    }


    /**
     * 根据 id 查询药品（带缓存）。
     *
     * @param id 药品主键 id
     * @return 药品信息
     */
    @Operation(summary = "根据id查询药品(带缓存)")
    @GetMapping("/getMedicineWithCache")
    public Result<Medicine> getMedicineWithCache(@RequestParam Long id) {
        return Result.success(medicineService.getMedicineWithCache(id));
    }

    /**
     * 扣减药品库存。
     *
     * @param id  药品主键 id
     * @param num 扣减数量
     * @return 是否扣减成功
     */
    @Operation(summary = "扣减库存")
    @PostMapping("/deductStock")
    @Transactional
    public Result<Boolean> deductStock(@RequestParam Long id, @RequestParam int num) {
        return Result.success(medicineService.deductStock(id, num));
    }
}
