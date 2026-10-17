package com.example.demo.dto;

import java.time.LocalDateTime;

public class ExceptionDto {
    private LocalDateTime timespan;
    private  int statuscode;
    private String error;
    private String message;
    private String path ;

    public int getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(int statuscode) {
        this.statuscode = statuscode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public LocalDateTime getTimespan() {
        return timespan;
    }

    public void setTimespan(LocalDateTime timespan) {
        this.timespan = timespan;
    }
}
