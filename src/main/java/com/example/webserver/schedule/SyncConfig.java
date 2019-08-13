package com.example.webserver.schedule;

import com.example.webserver.task.SyncUserTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SyncConfig {
    private static Logger logger = LoggerFactory.getLogger(SyncConfig.class);

    @Autowired
    private SyncUserTask syncUserTask;

    @Scheduled(cron = "0 0 1 * * ?")
    public void task() {
        logger.info(">>>>>>>>>>>>sync users task start");
        syncUserTask.sync();
    }
}