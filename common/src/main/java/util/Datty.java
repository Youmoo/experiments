package util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author youmoo
 * @since 2014-09-17 5:11 PM
 */
public class Datty {
    /**
     * 日期格式
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * 为每个线程分配一个日期格式化类,模式yyyy-MM-dd HH:mm:ss
     */
    private static final ThreadLocal<DateFormat> threadLocal =
            new ThreadLocal<DateFormat>() {
                protected DateFormat initialValue() {
                    return new SimpleDateFormat(DATE_FORMAT);
                }
            };

    /**
     * 工具类，不必实例化
     */
    private Datty() {
    }

    /**
     * 增减日期的天数
     *
     * @param date   传入日期
     * @param amount 偏移量
     * @return 偏移amount天后的日期
     */
    public static Date addDays(Date date, int amount) {
        return add(date, Calendar.DAY_OF_MONTH, amount);
    }

    /**
     * 根据传入的时间，返回与之 年份/月份/日期/小时/分钟/秒 相距offset的时间
     *
     * @param date   传入的时间
     * @param fileds 年份/月份/日期/小时/分钟/秒
     * @param offset 偏移时间
     * @return java.util.Date
     */
    private static Date add(Date date, int fileds, int offset) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(fileds, offset);
        return calendar.getTime();
    }

    /**
     * 将日期或日期时间字符串解析为日期时间，格式：yyyy-MM-dd HH:mm:ss
     *
     * @param dateOrDatetime 日期字符串
     * @return java.util.Date
     */
    public static Date toDate(String dateOrDatetime) {
        return toDate(dateOrDatetime, false, null);
    }

    private static Date toDate(String dateOrDatetime, boolean append, String whatToAppend) {
        if (append) {
            dateOrDatetime = dateOrDatetime.substring(0, 10) + " " + whatToAppend;
        } else if (dateOrDatetime.length() == 10) dateOrDatetime += " 00:00:00";


        try {
            return threadLocal.get().parse(dateOrDatetime);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
