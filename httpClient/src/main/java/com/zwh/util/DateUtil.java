package com.zwh.util;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @Description: 日期工具类
 */
@Slf4j
public class DateUtil {

    private static final String ALL_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String YYMMDDHHMMSS = "yyMMddHHmmss";

    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static final String YYYYMMDD = "yyyyMMdd";

    public static final String YYYY = "yyyy";

    public static final String YYYY_MM_DD_24H_MM_SS = YYYY_MM_DD + " HH:mm:ss";

    public static final String YYYY_MM_DD_12H_MM_SS = YYYY_MM_DD + " hh:mm:ss";

    public static final String YYYY_MM_DD_24H_MM = YYYY_MM_DD + " HH:mm";

    public static final ZoneId chinaZone = ZoneId.systemDefault(); // jdk 1.8 需要

    /**
     * 功能描述: 时间戳转日期
     *
     * @auther: weiyb
     * @date: 2018/8/3 16:37
     */
    public static String format(Long timeMillis, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.SIMPLIFIED_CHINESE);
        return sdf.format(timeMillis);
    }

    /**
     * 功能描述: 获取当前年
     *
     * @auther: weiyb
     * @date: 2018/8/3 16:37
     */
    public static String getNowYear() {
        return DateUtil.format(System.currentTimeMillis(), DateUtil.YYYY);
    }

    /**
     * 将yyyy-MM-dd 23:01:59 类型的字符串转为日期
     *
     * @param dateStr
     * @return
     */
    public static Date stringHHmmss2Date(String dateStr) {
        if (dateStr != null && !"".equals(dateStr)) {
            SimpleDateFormat sdf = new SimpleDateFormat(ALL_FORMAT);
            try {
                return sdf.parse(dateStr);
            } catch (Exception e) {

            }
        }
        return null;
    }

    /**
     * 将yyyy-MM-dd 类型的字符串转为日期
     *
     * @param dateStr
     * @return
     */
    public static Long string2Long(String dateStr, String pattern) {
        if (dateStr != null && !"".equals(dateStr)) {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            try {
                return sdf.parse(dateStr).getTime();
            } catch (Exception e) {

            }
        }
        return null;
    }

    public static Long string2Long(String dateStr) {
        return string2Long(dateStr, YYYY_MM_DD);
    }

    /**
     * 解析时间字符串成日期对象。
     * eg: System.out.println(DataUtil.parseDate("2011-3-1 12:09:00"));
     * System.out.println(DataUtil.parseDate("2000-1-1"));
     *
     * @param dateStr 支持日期和日期加时间两种格式的解析。如：“2000-1-1”和 "2011-3-1 12:09:00"
     * @return Date 日期对象
     * @throws Exception
     */
    public static Long parseDateLong(String dateStr) {
        Long d = null;
        try {
            SimpleDateFormat sim = null;
            if (dateStr.indexOf(":") > 0) {
                sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            } else {
                sim = new SimpleDateFormat("yyyy-MM-dd");
            }
            d = sim.parse(dateStr).getTime();
        } catch (ParseException e) {
            log.error("日期转换异常", e);
        }
        return d;
    }


    /**
     * @Auther: liujidong
     * @Date: 2019/5/17 10:25
     * @Description: 将时间为 YYYY-MM-dd 的字符串转为 Date  type 为时间类型 (1.开始时间 ......开始时间不需要加尾缀 2 结束时间 ...结尾时间加 " 23:59:59")
     */

    public static Date string2Date(String dateStr, Integer type) {

        if (StringUtils.isNotBlank(dateStr)) {
            if (dateStr.indexOf(":") < 0) { // 判断时间格式是否为 yyyy-MM-dd
                if (type.equals(1)) {
                    dateStr = dateStr + " 00:00:00";
                } else if (type.equals(2)) {
                    dateStr = dateStr + " 23:59:59";
                }
            }
            return stringHHmmss2Date(dateStr);
        }
        return null;
    }


    /**
     * @Auther: liujidong
     * @Date: 2019/5/17 10:25
     * @Description: 将时间为 YYYY-MM-dd 的字符串转为 Date  type 为时间类型 (1.开始时间 ......开始时间不需要加尾缀 2 结束时间 ...结尾时间加 " 23:59:59")
     */

    public static Date string2LocalDate(String dateStr, Integer type) {

        if (StringUtils.isNotBlank(dateStr)) {
            if (dateStr.indexOf(":") < 0) { // 判断时间格式是否为 yyyy-MM-dd
                if (type.equals(1)) {
                    dateStr = dateStr + " 00:00:00";
                } else if (type.equals(2)) {
                    dateStr = dateStr + " 23:59:59";
                }
            }
            return stringHHmmss2Date(dateStr);
        }
        return null;
    }

    /*****************  @Auther: liujidong  @Date: 2019/5/22 9:58    @Description: 以下方法支持jdk 1.8 的如期处理*****************************************/
    /**
     * 日期时间对象转换为日期对象
     *
     * @param localDateTime 日期时间对象
     * @return 日期对象
     */
    public static LocalDate dateTime2Date(LocalDateTime localDateTime) {
        LocalDate date = null;
        try {
            date = localDateTime.toLocalDate();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return date;
    }

    /**
     * 日期对象转换为日期时间对象
     *
     * @param localDate 日期对象
     * @return 日期时间对象
     */
    public static LocalDateTime date2DateTIme(LocalDate localDate) {

        LocalDateTime date = null;
        try {
            date = LocalDateTime.of(localDate, LocalTime.NOON);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return date;
    }

    /**
     * 字符串转换为日期
     *
     * @param strDate 字符串日期
     * @return 日期对象 yyyy-mm-dd
     */
    public static LocalDate str2Date(String strDate) {
        LocalDate date = null;
        try {
            date = LocalDate.parse(strDate, DateTimeFormatter.ISO_DATE);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return date;
    }


    /**
     * 日期对象转换为字符串
     *
     * @param localDate 日期对象
     * @return 日期字符串 yyyy-mm-dd
     */
    public static String date2Str(LocalDate localDate) {

        String date = null;
        try {
            date = localDate.format(DateTimeFormatter.ISO_DATE);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return date;
    }


    /**
     * 日期时间对象转换为字符串
     *
     * @param localDateTime 日期时间对象
     * @return 日期字符串 yyyy-MM-dd HH:mm:ss
     */
    public static String dateTime2Str(LocalDateTime localDateTime) {

        String date = null;
        try {
            if(localDateTime != null){
                date = localDateTime.format(DateTimeFormatter.ofPattern(ALL_FORMAT));
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return date;
    }

    /**
     * 字符串对象转换为日期时间
     *
     * @param dateTime 日期时间字符串
     * @return 日期时间对象
     */
    public static LocalDateTime dateStr2Time(String dateTime) {
        LocalDateTime date = null;
        try {
            date = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(ALL_FORMAT));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return date;
    }

    /**
     * 字符串对象转换为日期时间
     *
     * @param localDateTime 日期时间字符串
     * @return 日期时间对象
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        Date date = null;
        try {
            ZoneId zone = ZoneId.systemDefault();
            Instant instant = localDateTime.atZone(zone).toInstant();
            date = Date.from(instant);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * @description: 将localDateTime转为字符
     * @author zhouzq
     * @date 2019/6/9 17:26
     */
    public static String dateTime2Str(LocalDateTime localDateTime, String pattern) {
        if (localDateTime != null) {
            String date = null;
            try {
                date = localDateTime.format(DateTimeFormatter.ofPattern(pattern));
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
            return date;
        }
        return null;
    }

    /**
     * @author zhouzq
     * @date 2019/5/21 13:52
     * @description: 将 Long类型的日期转为 LocalDateTime 时间类型
     */

    public static LocalDateTime long2LocalDate(Long LongTime) {
        if (LongTime != null && !("").equals(LongTime)) {
            try {
                return LocalDateTime.ofInstant(Instant.ofEpochMilli(LongTime), ZoneId.systemDefault());
            } catch (Exception ex) {

            }
        }
        return null;
    }

    /**
     * @description: 将日期形转为yyyy-MM-dd 23:01:59形式
     * @author zhouzq
     * @date 2019/6/10 15:41
     */
    public static String date2Str(Date date) {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(ALL_FORMAT);
            return sdf.format(date);
        } else {
            return "";
        }
    }

    /**
     * @description: 获取当前天的前一天
     * @author zhouzq
     * @date 2019/7/9 13:38
     */
    public static LocalDateTime getYesterDay(){
        Calendar now = Calendar.getInstance();
        // 获取当前是几月
        int month = now.get(Calendar.MONTH) + 1;
        // 获取昨天是几号，如果-1为0则获取上个月最后一天
        int lastday = now.get(Calendar.DAY_OF_MONTH) - 1;
        // 如果今天是1号，则获取上月最后一天
        if (lastday == 0) {
            now.set(Calendar.DAY_OF_MONTH, 1);
            now.add(Calendar.DATE, -1);
            //上个月
            month = month - 1;
            //上个月最后一天
            lastday = now.getActualMaximum(Calendar.DATE);
        }
        LocalDateTime yesterDay = LocalDateTime.of(
                now.get(Calendar.YEAR),
                month,
                lastday,
                now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE),
                now.get(Calendar.SECOND)
        );
        return yesterDay;
    }

    public static LocalDateTime dataStr2local(String time){
        String timestr = DateUtil.parseDateLong(time.substring(0, time.indexOf("T"))).toString();
        LocalDateTime localDateTime = DateUtil.long2LocalDate(Long.parseLong(timestr));
        return localDateTime;
    }


    public static void main(String[] args) {
        System.out.println(format(System.currentTimeMillis(), YYMMDDHHMMSS));
    }
}
