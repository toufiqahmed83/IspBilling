package com.isp.billing.conf;

import com.aye.smsapi.smsapi.SmsApi;
import com.isp.billing.model.ClientInfo;
import com.isp.billing.model.JobConfiguration;
import com.isp.billing.model.sms.SmsConfg;
import com.isp.billing.service.ClientInfoService;
import com.isp.billing.service.DashBoard.DBInterFaceHeaderService;
import com.isp.billing.service.InterfaceHeaderService;
import com.isp.billing.service.JobConfigurationService;
import com.isp.billing.service.JobConfigurationServiceImpl;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

import com.aye.smsapi.*;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.ScheduledFuture;

/**
 * Created by Toufiq on 7/26/2019.
 */
@Service
public class ScheduledTasks //extends TimerTask //implements //Runnable //SchedulingConfigurer
 {
    Scheduled scheduled;
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    @Autowired
    private SmsProgram smsProgram = new SmsProgram();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private DBInterFaceHeaderService dbInterFaceHeaderService;

    @Autowired
    private JobConfigurationServiceImpl jobConfigurationService;


    @Scheduled(cron = "0 0/1 * * * ?")
    public void reportCurrentTime() {

        smsProgram.actions();


        dbInterFaceHeaderService.updateDashBoard();
        dbInterFaceHeaderService.updateClientInfosDashBoard();

        log.info("The time is now {}", dateFormat.format(new Date()));
    }

    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setThreadNamePrefix("ThreadPoolTaskScheduler");
        scheduler.setPoolSize(10);
        scheduler.initialize();
        return scheduler;
    }

//    ScheduledTaskRegistrar scheduledTaskRegistrar;
//
//    ScheduledFuture future;


    public static String c = "*/5 * * * * *";

//    @PostConstruct
//    public void postConst()
//    {
//        for(JobConfiguration jj:this.jobConfigurationService.finAll())
//        {
//            System.out.println(jj.getId());
//        }
//    }


//    @Override
//    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
//        taskRegistrar.setScheduler(taskScheduler());
//
////        taskRegistrar.addTriggerTask(
////                () -> exec(),new CronTrigger(this.jobConfigurationService.findById(2L).getValue()),
////                new CronTrigger()
//////                new Runnable() {
//////                                         @Override
//////                                         public void run() {
//////                                             jobConfigurationService.loadConfigurations();
//////                                         }
//////                                     }, new Trigger() {
//////                                         @Override
//////                                         public Date nextExecutionTime(TriggerContext triggerContext) {
//////                                             return null;
//////                                         }
//////                                     }
////        );
//
//
////        scheduledTaskRegistrar.addTriggerTask(
////                () -> exec(),
////                new CronTrigger(c)),new Trigger(){
////            @Override
////            public Date nextExecutionTime(TriggerContext triggerContext) {
////                Calendar nextExecutionTime = new GregorianCalendar();
////                Date lastActualExecutionTime = triggerContext.lastActualExecutionTime();
////                nextExecutionTime.setTime(lastActualExecutionTime != null ? lastActualExecutionTime : new Date());
////                nextExecutionTime.add(Calendar.MILLISECOND,null);
//////                        Integer.parseInt(jobConfigurationService.finByStatus(true).get(0).getValue()));
////
////                return nextExecutionTime.getTime();
//////                return null;
////            }
////        };
//        if (scheduledTaskRegistrar == null) {
//            scheduledTaskRegistrar = taskRegistrar;
//        }
//        if (taskRegistrar.getScheduler() == null) {
//            taskRegistrar.setScheduler(taskScheduler());
//        }
//
//
//        taskRegistrar.addTriggerTask(new Runnable() {
//            @Override
//            public void run() {
//
//                jobConfigurationService.loadConfigurations();
//                System.out.println("sout<<<<>>>>");
//            }
//        }, new Trigger() {
//            @Override
//            public Date nextExecutionTime(TriggerContext triggerContext) {
//                Calendar nextExecutionTime = new GregorianCalendar();
//                Date lastActualExecutionTime = triggerContext.lastActualExecutionTime();
//                nextExecutionTime.setTime(lastActualExecutionTime != null ? lastActualExecutionTime : new Date());
//                nextExecutionTime.add(Calendar.SECOND, 7);
//                return nextExecutionTime.getTime();
//            }
//        });
//
//
//
//        for (JobConfiguration x : jobConfigurationService.jobConfigurations) {
//            taskRegistrar.addTriggerTask(new Runnable() {
//                @Override
//                public void run() {
////                    jobConfigurationService.loadConfigurations();
//                    System.out.println("sout");
//                        future.cancel(true);
//
//
//                }
//            }, new Trigger() {
//                @Override
//                public Date nextExecutionTime(TriggerContext triggerContext) {
//                    Date date = triggerContext.lastCompletionTime();
//                    Date nextExec = date;
//                    if (x.getStatus()) {
//                        System.out.println(x.getName() + "<--getName");
//                        String cron = x.getValue();
//                        log.info(cron);
//                        CronTrigger trigger = new CronTrigger(cron);
//                        future = taskRegistrar.getScheduler().schedule(() -> exec(), trigger);
//
//                        nextExec = trigger.nextExecutionTime(triggerContext);
////                    this.sequenceGenerator.next(date);
//                        System.out.println(nextExec);
//
//                    }
//                    return nextExec;
//                }
//            });

//            taskRegistrar.addTriggerTask(new Runnable() {
//                @Override
//                public void run() {
//                    // Do not put @Scheduled annotation above this method, we don't need it anymore.
//                    jobConfigurationService.loadConfigurations();
//                    future.cancel(true);
//
//                }
//            }, new Trigger() {
//
//                @Override
//                public Date nextExecutionTime(TriggerContext triggerContext) {
//                    Date date = triggerContext.lastCompletionTime();
//                    for (JobConfiguration x : jobConfigurationService.jobConfigurations) {
//                        if (x.getStatus()) {
//                            System.out.println(x.getName() + "<--getName");
//                            String cron = x.getValue();
//                            log.info(cron);
//                            CronTrigger trigger = new CronTrigger(cron);
//                            future = taskRegistrar.getScheduler().schedule(() -> exec(), trigger);
//
//                            Date nextExec = trigger.nextExecutionTime(triggerContext);
////                    this.sequenceGenerator.next(date);
//                            System.out.println(nextExec);
//                            return nextExec;
//
//                        }
//                    }
//                    return null;
//                }
//            });

//        cancelTasks(true);
//        for (JobConfiguration j : this.jobConfigurationService.finAll()) {
//            if (j.getStatus()) {
//                CronTrigger croneTrigger = new CronTrigger(j.getValue(), TimeZone.getDefault());
//                future = taskRegistrar.getScheduler().schedule(() -> exec(), croneTrigger);
//
//
////                taskRegistrar.addCronTask();
////                        () -> exec(),
////                        new CronTrigger(j.getValue()));
////                if (scheduledTaskRegistrar == null) {
////                    scheduledTaskRegistrar = taskRegistrar;
////                }
////                if (taskRegistrar.getScheduler() == null) {
////                    taskRegistrar.setScheduler(taskScheduler());
////                }
////
////
////                // or cron way
////                CronTrigger croneTrigger = new CronTrigger(j.getValue(), TimeZone.getDefault());
////                future = taskRegistrar.getScheduler().schedule(() -> exec(), croneTrigger);
//
//            }
//
//        }
//
//    }


//    @PostConstruct
////    @Bean
//    public void reScheduled() {
//        List<JobConfiguration> jobConfigurations = new ArrayList<>();
//        jobConfigurations = this.jobConfigurationService.finAll();
//        for (JobConfiguration j : jobConfigurations) {
//            System.out.println(j.getName());
//            setScheduled(j.getValue(), j.getStatus());
//        }
//    }


    //        @SuppressWarnings("rawtypes")
//    private ScheduledFuture scheduledFuture;
//    //
//    private TaskScheduler taskScheduler;
//
//    public void setScheduled(String cronExpressionStr, Boolean isActive) {
//        if (taskScheduler == null) {
//            this.taskScheduler = new ConcurrentTaskScheduler();
//        }
//        System.out.println(isActive+"<---->"+cronExpressionStr);
//        if (this.scheduledFuture != null && !isActive) {
//            this.scheduledFuture.cancel(true);
//        }
////        if (this.scheduledFuture() != null) {
////            this.scheduledFuture().cancel(true);
////        }
//        this.scheduledFuture = this.taskScheduler.schedule(this, new CronTrigger(cronExpressionStr));
//
//    }

    //    @Override

//    public void cancelTasks(boolean mayInterruptIfRunning) {
//        log.info("Cancelling all tasks");
//        future.cancel(mayInterruptIfRunning); // set to false if you want the running task to be completed first.
//    }

//    @Bean
//    public void exec() {
//        log.info("The new time is now {}", dateFormat.format(new Date()));
////                dbInterFaceHeaderService.updateDashBoard();
//    }
}
