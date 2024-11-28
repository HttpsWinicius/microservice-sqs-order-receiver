package com.sqs.microservice.receiver.util;

public class TimeLoggerUtil {

    protected long startTimeLogger() {
        return System.currentTimeMillis();
    }

    protected long endTimeLogger(Long startTime) {
        long endTime = System.currentTimeMillis();
        return (endTime - startTime) / 1000;
    }

}
