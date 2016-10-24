package com.evan.demo.model.exception;

import com.evan.demo.model.bean.response.base.HttpResponse;

/**
 * 自定义异常
 * Created by evanyu on 16/9/19.
 */
public class ApiException extends RuntimeException {

    private HttpResponse mResponse;

    public ApiException(HttpResponse response) {
        mResponse = response;
    }

    public HttpResponse getErrorResponse() {
        return mResponse;
    }

    @Override
    public String toString() {
        return "ApiException{" +
                "code=" + mResponse.code +
                ", status='" + mResponse.status + '\'' +
                ", msg='" + mResponse.msg + '\'' +
                '}';
    }
}
