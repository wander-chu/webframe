package com.example.webserver.retry;

import com.github.rholder.retry.Attempt;
import com.github.rholder.retry.RetryListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyRetryListener implements RetryListener {
    Logger logger = LoggerFactory.getLogger(MyRetryListener.class);

    @Override
    public <Boolean> void onRetry(Attempt<Boolean> attempt) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[retry]time=" + attempt.getAttemptNumber());
        stringBuilder.append(", delay=" + attempt.getDelaySinceFirstAttempt());
        if (attempt.hasException()) {
            stringBuilder.append(", hasException=" + attempt.hasException());
            stringBuilder.append(", causeBy=" + attempt.getExceptionCause().toString());
            logger.warn(stringBuilder.toString());
        } else {
            stringBuilder.append(", hasResult=" + attempt.hasResult());
            stringBuilder.append(", result=" + attempt.getResult());
            if (!(java.lang.Boolean) attempt.getResult()) {
                logger.warn(stringBuilder.toString());
            }
        }
    }
}