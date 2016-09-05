package com.evan.demo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 * Created by evanyu on 16/6/4.
 */
public class DateUtils {

    public static final String FORMAT_END_WITH_DATE = "yyyy-MM-dd";
    public static final String FORMAT_END_WITH_SECONDS = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_DEFAULT = FORMAT_END_WITH_DATE;

    /**
     * 获取当前时间的格式化文本信息(默认时间格式)
     * @return 当前时间的的格式化文本信息
     */
    public static String getFormatDate(){
        return getFormatDate(System.currentTimeMillis(), FORMAT_DEFAULT);
    }

    /**
     * 获取指定时间的格式化文本信息(默认时间格式)
     * @return 指定时间的格式化文本信息
     */
    public static String getFormatDate(long time){
        return getFormatDate(time, FORMAT_DEFAULT);
    }

    /**
     * 获取当前时间的格式化文本信息(默认时间格式)
     * @param format 时间格式(参照 SimpleDateFormat的format 参数标准)
     * @return 当前时间的格式化文本信息
     */
    public static String getFormatDate(String format) {
        return getFormatDate(System.currentTimeMillis(), format);
    }

    /**
     * 获取指定时间的格式化文本信息(指定时间格式)
     * @param time 指定的时间(某一瞬间的毫秒值)
     * @param format 时间格式(参照 SimpleDateFormat的format 参数标准)
     * @return 指定时间的格式化文本信息
     */
    public static String getFormatDate(long time, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(time));
    }

}
