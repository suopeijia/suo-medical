package com.suo.medical.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suo.medical.entity.Medicine;
import com.suo.medical.mapper.MedicineMapper;
import com.suo.medical.service.MedicineService;
import org.springframework.stereotype.Service;

@Service
public class MedicineServiceImpl extends ServiceImpl<MedicineMapper, Medicine>
        implements MedicineService {

}
