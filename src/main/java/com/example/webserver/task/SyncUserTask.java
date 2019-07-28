package com.example.webserver.task;

import com.example.webserver.retry.MyRetryListener;
import com.example.webserver.service.UserService;
import com.github.rholder.retry.*;
import com.google.common.base.Predicates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Component
public class SyncUserTask {
    private static Logger logger = LoggerFactory.getLogger(SyncUserTask.class);

    @Autowired
    private UserService userService;

    @Async
    public void sync() {
        Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
                .retryIfException()
                .retryIfResult(Predicates.equalTo(false))
                .withWaitStrategy(WaitStrategies.fixedWait(1, TimeUnit.SECONDS))
                .withStopStrategy(StopStrategies.stopAfterAttempt(3))
                .withRetryListener(new MyRetryListener())
                .build();
        try {
            retryer.call(syncUsers);
            logger.info("sync users retry success");
        } catch (ExecutionException e) {
            logger.error("sync users execute failed:", e);
        } catch (RetryException e) {
            logger.error("sync users retry failed");
        }
    }

    private Callable<Boolean> syncUsers = new Callable<Boolean>() {
        @Override
        public Boolean call() throws Exception {
            int i = ThreadLocalRandom.current().nextInt(3);
            if (i == 0) {
                int userCount = userService.getUsers().size();
                logger.info("schedule task: user items[{}]", userCount);
                return true;
            } else if (i == 1) {
                return false;
            } else {
                throw new RuntimeException("get users failed");
            }
        }
    };
}
