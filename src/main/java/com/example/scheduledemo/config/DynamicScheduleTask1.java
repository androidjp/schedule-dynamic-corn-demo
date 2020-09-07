package com.example.scheduledemo.config;

import com.example.scheduledemo.GlobalAppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Jasper Wu
 * @date 10:23 AM 9/7/2020
 **/
@Component
public class DynamicScheduleTask1 implements SchedulingConfigurer {
    private static final Logger LOG = LoggerFactory.getLogger(DynamicScheduleTask1.class);
    @Resource
    private MongoTemplate mongoTemplate;

    public String cron = "0/5 * * * * ?";

    public DynamicScheduleTask1() {
        LOG.error("ScheduleConfig init()");
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {

        scheduledTaskRegistrar.addTriggerTask(new Runnable() {
            @Override
            public void run() {
                LOG.error("cronTask is running...");
            }
        }, new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                final List<GlobalAppConfig> globalAppConfigs = mongoTemplate.find(Query.query(Criteria.where("key").is("config01")), GlobalAppConfig.class);
                if (!globalAppConfigs.isEmpty()) {
                    cron = globalAppConfigs.get(0).getValue();
                }
                final CronTrigger cronTrigger = new CronTrigger(cron);
                final Date nextExecDate = cronTrigger.nextExecutionTime(triggerContext);
                return nextExecDate;
            }
        });
    }
}
