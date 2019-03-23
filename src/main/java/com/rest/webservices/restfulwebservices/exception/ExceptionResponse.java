package com.rest.webservices.restfulwebservices.exception;

import java.util.Date;

public class ExceptionResponse {

    private Date timeStamp;
    private String detail;
    private String message;

    public ExceptionResponse(Date timeStamp, String detail, String message) {
        this.timeStamp = timeStamp;
        this.detail = detail;
        this.message = message;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
