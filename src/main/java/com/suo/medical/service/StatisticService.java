package com.suo.medical.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.suo.medical.VO.DashboardVO;

import java.util.concurrent.ExecutionException;

public interface StatisticService {
    DashboardVO getDashboardVO() throws ExecutionException, InterruptedException;
}
