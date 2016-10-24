package com.evan.demo.model.bean.response.base;

import com.evan.demo.model.constant.NetConstant;

/**
 * 通用的网络访问对象
 * Created by evanyu on 16/9/19.
 */
public class HttpResponse/*<T>*/{

    public int code; // 返回码
    public String status; // 返回状态
    public String msg; // 返回说明
    // public T data; // 内容

    public boolean isSuccess() {
        return code == NetConstant.CODE_SUCCESS;
    }

    public boolean isError() {
        return code == NetConstant.CODE_ERROR;
    }

    /**
     * 判断是否已断开与服务端的session(会话)
     * 某些接口访问时需要保持session,这些接口如果未登录或登录已超时则表示已断开与服务端的session
     */
    public boolean isSessionOff() {
        return code == NetConstant.CODE_TIMEOUT;
    }
}
