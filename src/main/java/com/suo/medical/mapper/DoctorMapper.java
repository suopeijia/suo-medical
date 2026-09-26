package com.suo.medical.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.suo.medical.VO.DoctorVO;
import com.suo.medical.entity.Doctor;
import org.apache.ibatis.annotations.Select;

public interface DoctorMapper extends BaseMapper<Doctor> {

    @Select("select d.*,dept.name as departmentName from doctor d inner join department dept on d.department_id = dept.id where d.id = #{id}")
    DoctorVO selectDoctorWithDept(Long id);
}
