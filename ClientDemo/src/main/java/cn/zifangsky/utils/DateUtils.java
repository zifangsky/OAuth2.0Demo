package cn.zifangsky.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Date相关公共方法
 *
 * @author zifangsky
 * @date 2018/7/27
 * @since 1.0.0
 */
public class DateUtils {
    private static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 返回当前的LocalDateTime
     * @author zifangsky
     * @date 2018/7/30 13:23
     * @since 1.0.0
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime now(){
        return LocalDateTime.now();
    }

    /**
     * 返回当前时间字符串（格式化表达式：yyyy-MM-dd HH:mm:ss）
     * @author zifangsky
     * @date 2018/7/30 13:23
     * @since 1.0.0
     * @return java.lang.String
     */
    public static String nowStr(){
        return toDateTimeStr(now(), DEFAULT_DATE_TIME_FORMAT);
    }

    /**
     * 返回当前时间字符串
     * @author zifangsky
     * @date 2018/7/30 13:23
     * @since 1.0.0
     * @param pattern 指定时间格式化表达式
     * @return java.lang.String
     */
    public static String nowStr(String pattern){
        return toDateTimeStr(now(), pattern);
    }

    /**
     * 返回当前精确到秒的时间戳
     * @author zifangsky
     * @date 2018/7/30 13:23
     * @since 1.0.0
     * @param zoneOffset 时区，不填默认为+8
     * @return java.lang.Long
     */
    public static Long nowLong(ZoneOffset zoneOffset){
        LocalDateTime dateTime = now();

        if(zoneOffset == null){
            return dateTime.toEpochSecond(ZoneOffset.ofHours(8));
        }else{
            return dateTime.toEpochSecond(zoneOffset);
        }
    }

    /**
     * 返回当前精确到毫秒的时间戳
     * @author zifangsky
     * @date 2018/7/30 13:23
     * @since 1.0.0
     * @return java.lang.Long
     */
    public static Long currentTimeMillis(){
        return System.currentTimeMillis();
    }

    /**
     * 将时间戳转换为LocalDateTime
     * @author zifangsky
     * @date 2018/7/30 13:23
     * @since 1.0.0
     * @param second Long类型的时间戳
     * @param zoneOffset 时区，不填默认为+8
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime ofEpochSecond(Long second, ZoneOffset zoneOffset){
        if(zoneOffset == null){
            return LocalDateTime.ofEpochSecond(second,0,ZoneOffset.ofHours(8));
        }else{
            return LocalDateTime.ofEpochSecond(second,0,zoneOffset);
        }
    }

    /**
     * 格式化LocalDateTime（格式化表达式：yyyy-MM-dd HH:mm:ss）
     * @author zifangsky
     * @date 2018/7/30 13:23
     * @since 1.0.0
     * @return java.lang.String
     */
    public static String toDateTimeStr(LocalDateTime dateTime){
        return toDateTimeStr(dateTime, DEFAULT_DATE_TIME_FORMAT);
    }

    /**
     * 格式化LocalDateTime
     * @author zifangsky
     * @date 2018/7/30 13:23
     * @since 1.0.0
     * @param pattern 指定时间格式化表达式
     * @return java.lang.String
     */
    public static String toDateTimeStr(LocalDateTime dateTime, String pattern){
        return dateTime.format(DateTimeFormatter.ofPattern(pattern, Locale.SIMPLIFIED_CHINESE));
    }

    /**
     * 将时间字符串转化为LocalDateTime
     * @author zifangsky
     * @date 2018/7/30 13:48
     * @since 1.0.0
     * @param dateTimeStr 时间字符串
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime toDateTime(String dateTimeStr){
        return toDateTime(dateTimeStr, DEFAULT_DATE_TIME_FORMAT);
    }
    
    /**
     * 将时间字符串转化为LocalDateTime
     * @author zifangsky
     * @date 2018/7/30 13:48
     * @since 1.0.0
     * @param dateTimeStr 时间字符串
     * @param pattern 指定时间格式化表达式
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime toDateTime(String dateTimeStr, String pattern){
        return LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern(pattern, Locale.SIMPLIFIED_CHINESE));
    }
    
    /**
     * 返回当前日期的LocalDate
     * @author zifangsky
     * @date 2018/7/30 13:37
     * @since 1.0.0
     * @return java.time.LocalDate
     */
    public static LocalDate currentDate(){
        return LocalDate.now();
    }

    /**
     * 返回当前日期字符串（格式化表达式：yyyy-MM-dd）
     * @author zifangsky
     * @date 2018/7/30 13:42
     * @since 1.0.0
     * @return java.lang.String
     */
    public static String currentDateStr(){
        return toDateStr(currentDate());
    }

    /**
     * 格式化LocalDate
     * @author zifangsky
     * @date 2018/7/30 13:42
     * @since 1.0.0
     * @param date LocalDate
     * @return java.lang.String
     */
    public static String toDateStr(LocalDate date){
        return toDateStr(date, DEFAULT_DATE_FORMAT);
    }

    /**
     * 格式化LocalDate
     * @author zifangsky
     * @date 2018/7/30 13:42
     * @since 1.0.0
     * @param date LocalDate
     * @param pattern 指定日期格式化表达式
     * @return java.lang.String
     */
    public static String toDateStr(LocalDate date, String pattern){
        return date.format(DateTimeFormatter.ofPattern(pattern, Locale.SIMPLIFIED_CHINESE));
    }

    /**
     * 将日期字符串转化为LocalDate
     * @author zifangsky
     * @date 2018/7/30 13:48
     * @since 1.0.0
     * @param dateStr 日期字符串
     * @return java.time.LocalDate
     */
    public static LocalDate toDate(String dateStr){
        return toDate(dateStr, DEFAULT_DATE_FORMAT);
    }

    /**
     * 将日期字符串转化为LocalDate
     * @author zifangsky
     * @date 2018/7/30 13:48
     * @since 1.0.0
     * @param dateStr 日期字符串
     * @param pattern 指定日期格式化表达式
     * @return java.time.LocalDate
     */
    public static LocalDate toDate(String dateStr, String pattern){
        return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(pattern, Locale.SIMPLIFIED_CHINESE));
    }

    /**
     * 返回几天之后的时间
     * @author zifangsky
     * @date 2018/8/18 17:36
     * @since 1.0.0
     * @param days 天数
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime nextDays(Long days){
        return now().plusDays(days);
    }

    /**
     * 返回几天之后的时间（精确到秒的时间戳）
     * @author zifangsky
     * @date 2018/8/18 17:36
     * @since 1.0.0
     * @param days 天数
     * @param zoneOffset 时区，不填默认为+8
     * @return java.lang.Long
     */
    public static Long nextDaysSecond(Long days, ZoneOffset zoneOffset){
        LocalDateTime dateTime = nextDays(days);

        if(zoneOffset == null){
            return dateTime.toEpochSecond(ZoneOffset.ofHours(8));
        }else{
            return dateTime.toEpochSecond(zoneOffset);
        }
    }

    /**
     * 将天数转化为秒数
     * @author zifangsky
     * @date 2018/8/18 17:45
     * @since 1.0.0
     * @param days 天数
     * @return java.lang.Integer
     */
    public static Long dayToSecond(Long days){
        return days * 86400;
    }

}
