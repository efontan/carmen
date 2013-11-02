package com.despegar.hackaton.carmen.web.session.dto;

public enum ResponseErrorCode {

    ERROR_CODE("1"), SUCCESS_CODE("0");

    private String errorCode;

    ResponseErrorCode(String errorCode) {
        this.setErrorCode(errorCode);
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
