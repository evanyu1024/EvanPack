package com.evan.demo.model.constant;

/**
 * Created by evanyu on 16/5/31.
 */
public interface NetConstant {

    /** K780数据 */
    String APP_KEY = "12785";
    String SIGN = "af1413e4a572b4e9732a6aae4d154920";
    String BASE_URL = "http://api.k780.com:88/";

    /** 状态码 */
    int CODE_SUCCESS = 0; // 访问超时
    int CODE_TIMEOUT = -2; // 不在登录状态(未登录或登录已超时)
    int CODE_ERROR = -1; // 未知错误

}
