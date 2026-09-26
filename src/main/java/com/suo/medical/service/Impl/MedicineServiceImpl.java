package com.suo.medical.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suo.medical.common.enums.ResultCode;
import com.suo.medical.common.exception.BusinessException;
import com.suo.medical.entity.Medicine;
import com.suo.medical.mapper.MedicineMapper;
import com.suo.medical.service.MedicineService;
import io.lettuce.core.RedisClient;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.jar.JarEntry;

@Service
@RequiredArgsConstructor
public class MedicineServiceImpl extends ServiceImpl<MedicineMapper, Medicine>
        implements MedicineService {

    private final MedicineMapper medicineMapper;

    private final RedisTemplate redisTemplate;

    private final RedissonClient redissonClient;

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
            long expireTime = 30*60+new Random().nextInt(10) + 1;
            if(medicine != null){
                redisTemplate.opsForValue().set(key,medicine,expireTime, TimeUnit.SECONDS);
            }else {
                redisTemplate.opsForValue().set(key,null,120, TimeUnit.SECONDS);
            }

            return medicine;
        }
    }

    @Override
    public Medicine updateByIdBySelf(Medicine medicine) {
        //先更新Mysql
        medicineMapper.updateById(medicine);
        //再删除缓存
        redisTemplate.delete("medicine:" + medicine.getId());
        return medicine;
    }

    @Override
    public Boolean deductStock(Long id, int num) {
        //拿到锁对象
        RLock lock = redissonClient.getLock("lock:medicine:"+id);
        //加锁
        lock.lock();
        try{
            //在锁内做“查库存”并进行判断
            Medicine medicine = medicineMapper.selectById(id);
            if(medicine.getStock() < num){
                throw new BusinessException(ResultCode.STOCK_NOT_ENOUGH);
            }
            medicine.setStock(medicine.getStock() - num);
            medicineMapper.updateById(medicine);
            //删缓存
            redisTemplate.delete("medicine:" + medicine.getId());
        }finally {
            //释放锁
            lock.unlock();
        }
        return null;
    }
}
