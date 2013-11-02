package com.despegar.hackaton.carmen.web.controller.response;

public class Response<T> {
    ResponseStatus responseStatus;
    T data;

    public Response() {
    }

    public Response(ResponseStatus responseStatus, T data) {
        super();
        this.responseStatus = responseStatus;
        this.data = data;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseStatus getResponseStatus() {
        return this.responseStatus;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

}
