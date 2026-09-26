package com.suo.medical.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.suo.medical.entity.Medicine;
import org.apache.ibatis.annotations.Delete;

public interface MedicineMapper extends BaseMapper<Medicine> {

    @Delete("delete from medicine where id = ${id}")
    Boolean removeById(Long id);
}
