package com.suo.medical.service.Impl;

import com.suo.medical.VO.DashboardVO;
import com.suo.medical.config.ThreadPoolConfig;
import com.suo.medical.mapper.DepartmentMapper;
import com.suo.medical.mapper.DoctorMapper;
import com.suo.medical.mapper.MedicineMapper;
import com.suo.medical.mapper.PatientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

import com.suo.medical.service.StatisticService;

import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

@Service
@RequiredArgsConstructor

public class StatisticServiceImpl implements StatisticService {

    /**
     * 注入自定义线程池
     */
    private final ThreadPoolConfig threadPoolConfig;

    /**
     * 注入4个查询Mapper
     * @return
     */
    private final PatientMapper patientMapper;
    private final DoctorMapper doctorMapper;
    private final DepartmentMapper departmentMapper;
    private final MedicineMapper medicineMapper;
    private final ThreadPoolExecutor bizThreadPool;

    @Override
    public DashboardVO getDashboardVO() throws ExecutionException, InterruptedException {
        Future<Long> pf = bizThreadPool.submit(()->patientMapper.selectCount(null));
        Future<Long> df = bizThreadPool.submit(()->doctorMapper.selectCount(null));
        Future<Long> deptF = bizThreadPool.submit(()->departmentMapper.selectCount(null));
        Future<Long> mf = bizThreadPool.submit(()->medicineMapper.selectCount(null));
        DashboardVO vo = new DashboardVO();
        vo.setPatientCount(pf.get());
        vo.setDoctorCount(df.get());
        vo.setDepartmentCount(deptF.get());
        vo.setMedicineCount(mf.get());
        return vo   ;
    }
}
