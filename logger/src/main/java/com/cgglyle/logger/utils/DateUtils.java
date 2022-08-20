package com.cgglyle.logger.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期转化工具
 *
 * @author lyle
 * @since 2022/08/20
 */
public class DateUtils {
    private final static String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss:SSSSSSSSS";

    /**
     * 将时间戳转换成日期
     * <p></p>
     * 格式为：yyyy-MM-dd HH:mm:ss:SSSSSSSSS
     *
     * @param timestamp 时间戳
     * @return 日期
     */
    public static String timestampToDate(Long timestamp){
        return timestampToDate(timestamp, TIME_FORMAT);
    }

    /**
     * 将时间戳转换成日期
     *
     * @param timestamp 时间戳
     * @param timeFormat 时间格式 类似[yyyy-MM-dd HH:mm:ss:SSSSSSSSS]
     * @return 日期
     */
    public static String timestampToDate(Long timestamp, String timeFormat){
        Date date = new Date(timestamp);
        SimpleDateFormat format = new SimpleDateFormat(timeFormat);
        return format.format(date);
    }
}
