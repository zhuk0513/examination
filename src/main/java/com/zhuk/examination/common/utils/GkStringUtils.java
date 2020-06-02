package com.zhuk.examination.common.utils;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>
 * 自定义String工具类
 * </p>
 *
 * @author zhuyg
 * @since 2019-07-12
 */
@Component
public class GkStringUtils {

    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 判断多个入参参数是否为空
     *
     * @param strings
     * @return
     */
    public static boolean isEmpty(String... strings) {
        for (String str : strings) {
            if (str == null || "".equals(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     *  获取当前时间字符串
     * @param format
     * @return
     */
    public static String getDate(String format) {
        if(isEmpty(format)){
            format=("yyyy-MM-dd HH:mm:ss");
        }
        sdf.applyPattern(format);
        return sdf.format(new Date());
    }

    /**
     * 获取据今天某一天开始时间 00：00：00
     * @param date
     * @param day
     * @return
     */
    public static Date getStartTime(Date date,int day) {
        Calendar dateStart = Calendar.getInstance();
        dateStart.setTime(date);
        dateStart.set(Calendar.HOUR_OF_DAY, 24*day);
        dateStart.set(Calendar.MINUTE, 0);
        dateStart.set(Calendar.SECOND, 0);
        return dateStart.getTime();
    }

    /**
     *  获取据今天某一天的结束时间 23:59:59
     *
     * @param date
     * @param day
     * @return
     */
    public static Date getEndTime(Date date,int day) {
        Calendar dateEnd = Calendar.getInstance();
        dateEnd.setTime(date);
        dateEnd.set(Calendar.HOUR_OF_DAY, 24*day+23);
        dateEnd.set(Calendar.MINUTE, 59);
        dateEnd.set(Calendar.SECOND, 59);
        return dateEnd.getTime();
    }

    /**
     * 保存到mongodb时间
     * @param date
     * @return
     */
    public static Date getMongoNowTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, 8);
        return calendar.getTime();
    }

}
