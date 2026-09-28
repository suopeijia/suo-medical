package com.suo.medical.controller;

import com.suo.medical.VO.DashboardVO;
import com.suo.medical.common.response.Result;
import com.suo.medical.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

/**
 * 统计
 */
@RestController
@RequestMapping("/statictic")
@RequiredArgsConstructor
public class StatisticController {

    private final StatisticService statisticService;

    @RequestMapping("/dashboard")
    public Result<DashboardVO> Statistic() throws ExecutionException, InterruptedException {
        return Result.success(statisticService.getDashboardVO());
    }
}
