package com.example.scheduledemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Jasper Wu
 * @date 10:18 AM 9/7/2020
 **/
@Service
public class MyService {
    private static final Logger LOG = LoggerFactory.getLogger(MyService.class);

    @Scheduled(cron = "0/1 * * * * ?")
    public void run01() {
        LOG.info("run01...");
    }

}
