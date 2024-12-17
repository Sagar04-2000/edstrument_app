package com.edstruments.product.exception;

import java.util.Date;
import java.util.List;

public class ErrorDetails {

    private Date timestamp;
    private String message;
    private String details;

    private List<String> exceptionList;

    public ErrorDetails(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public ErrorDetails(Date timestamp, String message, List<String> exceptionList) {
        this.timestamp = timestamp;
        this.message = message;
        this.exceptionList = exceptionList;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public List<String> getExceptionList() {
        return exceptionList;
    }

    public void setExceptionList(List<String> exceptionList) {
        this.exceptionList = exceptionList;
    }
}
