package com.example.demo.dto;

import java.time.LocalDateTime;
import java.util.Map;

public class ValidationExceptionResponseDto {
    private LocalDateTime timespan;
    private  int statuscode;
    private String error;
    private String message;
    private String path ;
    private Map<String,String> fielderror;



    public ValidationExceptionResponseDto(LocalDateTime timespan, int statuscode, String error, String message, String path, Map<String, String> fielderror) {
        this.timespan = timespan;
        this.statuscode = statuscode;
        this.error = error;
        this.message = message;
        this.path = path;
        this.fielderror = fielderror;
    }

    public LocalDateTime getTimespan() {
        return timespan;
    }

    public void setTimespan(LocalDateTime timespan) {
        this.timespan = timespan;
    }

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

    public Map<String, String> getFielderror() {
        return fielderror;
    }


    public void setFielderror(Map<String, String> fielderror) {
        this.fielderror = fielderror;
    }
}
