package com.sqs.microservice.receiver.error;


import jakarta.websocket.OnError;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiError {

    private String message;
    private int statusCode;
    private long timestamp;


    public ApiError(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
        this.timestamp = System.currentTimeMillis();
    }
}
