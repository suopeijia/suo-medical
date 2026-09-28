package com.suo.medical.service.Impl;

import com.suo.medical.VO.DashboardVO;
import com.suo.medical.config.ThreadPoolConfig;
import com.suo.medical.mapper.DepartmentMapper;
import com.suo.medical.mapper.DoctorMapper;
import com.suo.medical.mapper.MedicineMapper;
import com.suo.medical.mapper.PatientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.suo.medical.service.StatisticService;

import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

@Service
@RequiredArgsConstructor

public class StatisticServiceImpl implements StatisticService {
    /**
     * Future是同步的，改用ComplateFuture异步实现
     */

//
//    /**
//     * 注入自定义线程池
//     */
//    private final ThreadPoolConfig threadPoolConfig;
//
//    /**
//     * 注入4个查询Mapper
//     * @return
//     */
//    private final PatientMapper patientMapper;
//    private final DoctorMapper doctorMapper;
//    private final DepartmentMapper departmentMapper;
//    private final MedicineMapper medicineMapper;
//   private final ThreadPoolExecutor bizThreadPool;
//
//    @Override
//    public DashboardVO getDashboardVO() throws ExecutionException, InterruptedException {
//        Future<Long> pf = bizThreadPool.submit(()->patientMapper.selectCount(null));
//        Future<Long> df = bizThreadPool.submit(()->doctorMapper.selectCount(null));
//        Future<Long> deptF = bizThreadPool.submit(()->departmentMapper.selectCount(null));
//        Future<Long> mf = bizThreadPool.submit(()->medicineMapper.selectCount(null));
//        DashboardVO vo = new DashboardVO();
//        vo.setPatientCount(pf.get());
//        vo.setDoctorCount(df.get());
//        vo.setDepartmentCount(deptF.get());
//        vo.setMedicineCount(mf.get());
//        return vo   ;
//    }


    private final PatientMapper patientMapper;
    private final DoctorMapper doctorMapper;
    private final DepartmentMapper departmentMapper;
    private final MedicineMapper medicineMapper;

    private final ThreadPoolConfig threadPoolConfig;




    /**
     * ComplateFuture异步实现
     */
    @Override
    public DashboardVO getDashboardVO() throws ExecutionException, InterruptedException {

        CompletableFuture<Long> patientCf = CompletableFuture.supplyAsync(
                () -> {
                    return patientMapper.selectCount(null);
                },
                threadPoolConfig.bizThreadPool()
        );
        CompletableFuture<Long> doctorCf = CompletableFuture.supplyAsync(
                () -> {
                    return doctorMapper.selectCount(null);
                },
                threadPoolConfig.bizThreadPool()
        );
        CompletableFuture<Long> deptCf = CompletableFuture.supplyAsync(
                () -> {
                    return departmentMapper.selectCount(null);
                },
                threadPoolConfig.bizThreadPool()
        );
        CompletableFuture<Long> medicineCf = CompletableFuture.supplyAsync(
                () -> {
                    return medicineMapper.selectCount(null);
                },
                threadPoolConfig.bizThreadPool()
        );
        //这样来传四个参数，然后链式调用join()方法把线程插入到主线程之前
        CompletableFuture.allOf(patientCf, doctorCf, deptCf, medicineCf).join();

        DashboardVO vo = new DashboardVO();
        vo.setDepartmentCount(deptCf.get());
        vo.setDoctorCount(doctorCf.get());
        vo.setMedicineCount(medicineCf.get());
        vo.setPatientCount(patientCf.get());
        return vo;
    }
}
