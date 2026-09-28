package com.suo.medical.service;

import com.suo.medical.common.response.Result;
import com.suo.medical.entity.Patient;
import com.suo.medical.tool.PageUtil;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 患者服务接口，定义患者的增删改查及分页查询业务。
 *
 * @author suo
 */
public interface PatientService {
    /**
     * 根据 id 获取患者信息。
     *
     * @param id 患者主键 id
     * @return 患者信息
     */
    Patient getPatientById(Long id);

    /**
     * 添加患者。
     *
     * @param patient 患者信息
     * @return 新增患者的主键 id
     */
    Long addPatient(Patient patient);

    /**
     * 更新患者信息。
     *
     * @param patient 患者信息
     */
    void updatePatient(Patient patient);

    /**
     * 根据 id 删除患者。
     *
     * @param id 患者主键 id
     * @return 删除影响的记录数
     */
    Long deleteById(Long id);

    /**
     * 分页查询患者，支持按姓名、年龄区间过滤。
     *
     * @param name    患者姓名，模糊匹配，可为空
     * @param minAge  最小年龄，可为空
     * @param maxAge  最大年龄，可为空
     * @param current 当前页码
     * @param size    每页大小
     * @return 分页患者信息
     */
    PageUtil<Patient> getPagePatient( String name, Integer minAge, Integer maxAge, Long current, Long size);
}
