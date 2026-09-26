package com.suo.medical.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.suo.medical.entity.Medicine;

public interface  MedicineService extends IService<Medicine> {
    Boolean removeById(Long id, boolean flag);

    Medicine getMedicineWithCache(Long id);

    Medicine updateByIdBySelf(Medicine medicine);

    /**
     * 扣减库存
     */
    Boolean deductStock(Long id, int num);
}
