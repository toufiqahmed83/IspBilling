package com.isp.billing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Toufiq on 8/6/2019.
 */
//@Service
public class SchedulerService //implements SchedulingConfigurer
{

    @Autowired
    private JobConfigurationService jobConfigurationService;

    @Bean
    public TaskScheduler poolScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setThreadNamePrefix("ThreadPoolTaskScheduler");
        scheduler.setPoolSize(1);
        scheduler.initialize();
        return scheduler;
    }

//    @Override
//    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
//        taskRegistrar.setScheduler(poolScheduler());
//        taskRegistrar.addTriggerTask(new Runnable() {
//            @Override
//            public void run() {
//                // Do not put @Scheduled annotation above this method, we don't need it anymore.
//                jobConfigurationService.finByStatus(true);
//            }
//        }, new Trigger() {
//            @Override
//            public Date nextExecutionTime(TriggerContext triggerContext) {
//                Calendar nextExecutionTime = new GregorianCalendar();
//                Date lastActualExecutionTime = triggerContext.lastActualExecutionTime();
//                nextExecutionTime.setTime(lastActualExecutionTime != null ? lastActualExecutionTime : new Date());
//                nextExecutionTime.add(Calendar.MILLISECOND, Integer.parseInt(jobConfigurationService.getJobConfig("ABC").getValue()));
//                return nextExecutionTime.getTime();
//            }
//        });
//
//    }
}
