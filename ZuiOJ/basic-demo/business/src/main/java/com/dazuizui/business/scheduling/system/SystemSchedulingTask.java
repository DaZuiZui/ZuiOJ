package com.dazuizui.business.scheduling.system;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 系统调度任务
 */
@Configuration
@EnableScheduling
public class SystemSchedulingTask {
    /**
     * 宣传定时任务
     */
    @Scheduled(fixedRate = 500000)
    private void configureTasks(){
        System.err.println("By Dazui from https://github.com/DaZuiZui");
        System.err.println("创作于 大嘴 来自 https://github.com/DaZuiZui");
    }
}
