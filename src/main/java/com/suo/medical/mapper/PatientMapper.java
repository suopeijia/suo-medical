package com.suo.medical.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.suo.medical.entity.Patient;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PatientMapper extends BaseMapper<Patient> {

}
