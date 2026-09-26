package com.suo.medical.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suo.medical.entity.Medicine;
import com.suo.medical.mapper.MedicineMapper;
import com.suo.medical.service.MedicineService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;
import java.util.jar.JarEntry;

@Service
@RequiredArgsConstructor
public class MedicineServiceImpl extends ServiceImpl<MedicineMapper, Medicine>
        implements MedicineService {

    private final MedicineMapper medicineMapper;

    private final RedisTemplate redisTemplate;


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

    /**
     * 查询药品：
     *      先查Redis,Redis有则返回
     *      Redis没有则查数据库，并放入Redis
     */
    @Operation(summary = "查询药品")
    public Medicine getMedicineWithCache(Long id){
        String key = "medicine:" + id;//key设计：业务前缀medicine:id
        //先查Redis,Redis有则返回
        //先查key，再查value
        Boolean keyExists = Boolean.TRUE.equals(redisTemplate.hasKey(key));
        if(keyExists.equals(Boolean.TRUE)){
            return (Medicine) redisTemplate.opsForValue().get(key);
        }else {
            //Redis没有则查数据库，并放入Redis
            Medicine medicine = medicineMapper.selectById(id);
            if(medicine != null){
                redisTemplate.opsForValue().set(key,medicine,30, TimeUnit.MINUTES);
            }else {
                redisTemplate.opsForValue().set(key,null);
            }

            return medicine;
        }
    }

}
