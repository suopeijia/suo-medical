package com.suo.medical.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.suo.medical.entity.Medicine;

/**
 * 药品服务接口，继承 MyBatis-Plus 的 IService 通用能力，并扩展删除、缓存查询、库存扣减等业务。
 *
 * @author suo
 */
public interface  MedicineService extends IService<Medicine> {
    /**
     * 根据 id 删除药品。
     *
     * @param id   药品主键 id
     * @param flag 删除标志
     * @return 是否删除成功
     */
    Boolean removeById(Long id, boolean flag);

    /**
     * 根据 id 查询药品（优先读缓存，未命中则查库并回填缓存）。
     *
     * @param id 药品主键 id
     * @return 药品信息
     */
    Medicine getMedicineWithCache(Long id);

    /**
     * 更新药品并删除对应缓存。
     *
     * @param medicine 药品信息
     * @return 更新后的药品信息
     */
    Medicine updateByIdBySelf(Medicine medicine);

    /**
     * 扣减库存。
     *
     * @param id  药品主键 id
     * @param num 扣减数量
     * @return 是否扣减成功
     */
    Boolean deductStock(Long id, int num);
}
