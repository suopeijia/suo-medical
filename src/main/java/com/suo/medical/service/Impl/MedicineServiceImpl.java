package com.suo.medical.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suo.medical.entity.Medicine;
import com.suo.medical.mapper.MedicineMapper;
import com.suo.medical.service.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.jar.JarEntry;

@Service
@RequiredArgsConstructor
public class MedicineServiceImpl extends ServiceImpl<MedicineMapper, Medicine>
        implements MedicineService {

    private final MedicineMapper medicineMapper;

    @Transactional
    public Boolean removeById(Long id, boolean flag) {
        medicineMapper.removeById(id);
        try {
            if(flag){
                throw new RuntimeException("删除失败");
            }
        }catch (RuntimeException e){
            throw new RuntimeException("删除失败");
        }
        return null;
    }

}
