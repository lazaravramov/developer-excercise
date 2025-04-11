package com.devexercise.developer.Exception.Response;

public class ExceptionResponse {
    private  Integer status;
    private  String message;
    private  String detail;

    public ExceptionResponse(Integer status, String message, String detail) {
        this.status = status;
        this.message = message;
        this.detail = detail;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
