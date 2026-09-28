package com.suo.medical.service;

import com.suo.medical.DTO.DoctorDTO;
import com.suo.medical.VO.DoctorVO;
import com.suo.medical.entity.Doctor;
import com.suo.medical.tool.PageUtil;

/**
 * 医生服务接口，定义医生的增删改查、分页查询及联表查询业务。
 *
 * @author suo
 */
public interface DoctorService {

    /**
     * 分页查询医生，支持按姓名模糊过滤。
     *
     * @param name    医生姓名，模糊匹配，可为空
     * @param current 当前页码
     * @param size    每页大小
     * @return 分页医生信息
     */
    PageUtil<Doctor> getPageDoctor(String name, Long current, Long size);

    /**
     * 新增医生。
     *
     * @param doctorDTO 医生入参
     * @return 新增后的医生信息
     */
    Doctor adddoctorDTO(DoctorDTO doctorDTO);

    /**
     * 修改医生。
     *
     * @param doctorDTO 医生入参
     * @return 修改后的医生信息
     */
    DoctorDTO updateDoctor(DoctorDTO doctorDTO);

    /**
     * 根据 id 查询医生。
     *
     * @param id 医生主键 id
     * @return 医生信息
     */
    DoctorDTO getByIdDoctor(Long id);

    /**
     * 根据 id 删除医生。
     *
     * @param id 医生主键 id
     * @return 是否删除成功
     */
    Boolean deleteDoctor(Long id);

    /**
     * 根据 id 联表查询医生及其科室信息。
     *
     * @param id 医生主键 id
     * @return 含科室名称的医生视图对象
     */
    DoctorVO getDoctorVOById(Long id);
}
