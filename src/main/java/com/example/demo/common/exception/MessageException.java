package com.example.demo.common.exception;

import java.time.LocalDateTime;

/**
 * @author huangyy@zjiec.com
 * @date 2020/11/04
 */
public class MessageException extends Exception{

    private final String message;

    private final LocalDateTime time;

    public MessageException(String message) {
        super(message);
        this.message = message;
        time = LocalDateTime.now();
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
