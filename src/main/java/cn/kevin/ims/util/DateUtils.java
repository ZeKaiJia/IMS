package cn.kevin.ims.util;

import org.apache.commons.lang.StringUtils;
import org.sqlite.date.DateFormatUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author kevin
 */
public class DateUtils {
    public static final long ONE_DAY_LONG = 86400000;
    private static DateUtils classInstance = new DateUtils();

    public static Long currentSecond() {
        return System.currentTimeMillis();
    }

    /**
     * Timestamp时间类型转换String
     *
     * @return String
     */
    public static String timestamp2string(Timestamp time, String pattern) {
        Date d = new Date(time.getTime());

        if (pattern == null) {
            pattern = "yyyy-MM-dd HH:mm:ss";
        }
        return DateFormatUtils.format(d, pattern);
    }

    /**
     * Date时间类型转换String
     * 时间格式yyyy-MM-dd HH:mm
     *
     * @return String
     */
    public static String formatDate(Date date, String pattern) {
        if (date == null) {
            date = new Date(System.currentTimeMillis());
        }

        if (pattern == null) {
            pattern = "yyyy-MM-dd HH:mm";
        }
        return DateFormatUtils.format(date, pattern);
    }

    /**
     * date传null获取当前时间
     * 时间格式yyyy-MM-dd HH:mm
     *
     * @return String
     */
    public static String defaultFormat(Date date) {
        return formatDate(date, null);
    }

    /**
     * 获取当前时间Date类型
     *
     * @return Date
     */
    public static Date parseDateFormat() {
        SimpleDateFormat fo = new SimpleDateFormat();
        Date date = new java.util.Date(System.currentTimeMillis());
        fo.applyPattern("yyyy-MM-dd");

        try {
            date = fo.parse(DateFormatUtils.format(date, "yyyy-MM-dd"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 根据Timestamp类型返回
     *
     * @return String
     */
    public static String parseTimestampFormat(Timestamp time) {

        if (time != null && !"".equals(time)) {
            return parseDateFormat(new Date(time.getTime()));
        } else {
            return "";
        }
    }

    /**
     * 根据Date转换String格式yyyy-MM-dd
     *
     * @return String
     */
    public static String parseDateFormat(Date date) {
        SimpleDateFormat fo = new SimpleDateFormat();
        fo.applyPattern("yyyy-MM-dd");
        String retVal = "0000-00-00";
        try {
            retVal = DateFormatUtils.format(date, "yyyy-MM-dd");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return retVal;
    }

    /**
     * 根据String返回Timestamp
     *
     * @return Timestamp
     */
    public static Timestamp formatFromYYYYMMDD(String value) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = sdf.parse(value);
        } catch (ParseException e) {
            return null;
        }
        return new Timestamp(date.getTime());
    }

    public static Timestamp formatFromYYYYMMDDhhmmss(String value) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date;
        try {
            date = sdf.parse(value);
        } catch (ParseException e) {
            return null;
        }
        return new Timestamp(date.getTime());
    }

    public static Date string2Date(String str) {
        if (StringUtils.isEmpty(str)) {
            return new Date();
        }
        return java.sql.Date.valueOf(str);
    }

    public static boolean between(Date srcDate, Date startDate, Date endDate) {
        if (startDate.after(srcDate)) {
            return false;
        }
        return !endDate.before(srcDate);
    }

    public static Date getDayStart(Date date) {
        return string2Date(divideDateForDay(date, "yyyy-MM-dd", 0));
        // return Timestamp.valueOf(formatDate(date, "yyyy-MM-dd")+" 00:00:00");
    }

    /**
     * 根据传入时间在追加一天
     */
    public static Date getDayEnd(Date date) {
        return string2Date(divideDateForDay(date, "yyyy-MM-dd", 1));
        // return Timestamp.valueOf(formatDate(date, "yyyy-MM-dd")+" 23:59:59");
    }

    /**
     * 给指定时间 追加天数
     *
     * @param pattern 显示格式
     * @param num     需要加的天数
     */
    public static String divideDateForDay(Date date, String pattern, int num) {
        if (date == null) {
            date = new Date(System.currentTimeMillis());
        }
        if (pattern == null) {
            pattern = "yyyy-MM-dd HH:mm";
        }
        Calendar cal = null;
        SimpleDateFormat fo = new SimpleDateFormat();
        fo.applyPattern(pattern);
        try {
            fo.format(date);
            cal = fo.getCalendar();
            cal.add(Calendar.DATE, num);
        } catch (Exception ignored) {
        }
        assert cal != null;
        return fo.format(cal.getTime());
    }

    /**
     * 当前日期前几天或者后几天的日期
     */
    public static Date afterNDay(int n) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, n);
        return c.getTime();
    }

    /**
     * 当前日期前几天或者后几天的日期
     */
    public static Date afterNDays(Timestamp time, int n) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(time.getTime());
        c.add(Calendar.DATE, n);
        return c.getTime();
    }

    public static Timestamp transDate(Date date) {
        if (date != null) {
            long time = date.getTime();
            return new Timestamp(time);
        }
        return null;
    }

    public static Date transTimestamp(Timestamp time) {
        if (time != null) {
            long t = time.getTime();
            return new Date(t);
        }
        return null;
    }

    /**
     * 时间段的第一个时间
     */
    public static java.sql.Timestamp stringToTime1(String d) {
        java.sql.Timestamp t = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        Date d1;
        try {
            if (StringUtils.isNotEmpty(d)) {
                d1 = df.parse(d);
                t = new Timestamp(d1.getTime());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 时间段的第二个时间
     */
    public static java.sql.Timestamp stringToTime2(String d) {
        java.sql.Timestamp t = null;
        //StringUtils
        if (StringUtils.isNotEmpty(d)) {
            t = Timestamp.valueOf(d + " 23:59:59");
        }
        return t;
    }

    public static Calendar getYesterDayBegin() {
        Calendar before = Calendar.getInstance();

        before
                .set(Calendar.DAY_OF_MONTH,
                        before.get(Calendar.DAY_OF_MONTH) - 1);
        before.set(Calendar.HOUR_OF_DAY, 0);
        before.set(Calendar.MINUTE, 0);
        before.set(Calendar.SECOND, 0);
        before.set(Calendar.MILLISECOND, 0);

        return before;
    }

    /**
     * 查看昨天的日期  还需要调用transCalendarToTimestamp方法
     */
    public static Calendar getYesterDayEnd() {
        Calendar after = Calendar.getInstance();
        after.set(Calendar.DAY_OF_MONTH, after.get(Calendar.DAY_OF_MONTH) - 1);
        after.set(Calendar.HOUR_OF_DAY, 23);
        after.set(Calendar.MINUTE, 59);
        after.set(Calendar.SECOND, 59);
        after.set(Calendar.MILLISECOND, 999);
        return after;
    }

    /**
     * Calendar和Timestamp转换
     */
    public static Timestamp transCalendarToTimestamp(Calendar cal) {
        return new Timestamp(cal.getTimeInMillis());
    }

    /**
     * 根据Timestamp类型参数  返回年后两位月日(例如:140606)
     *
     * @return String
     */
    public static String transTimestamptostr(Timestamp time) {
        if (time != null) {

            java.util.Calendar c = Calendar.getInstance();
            c.setTime(time);
            String year = String.valueOf(c.get(Calendar.YEAR));
            String month = String.valueOf(c.get(Calendar.MONTH) + 1);
            String day = String.valueOf(c.get(Calendar.DATE));

            if (month.length() < 2) {
                month = "0" + month;
            }
            if (day.length() < 2) {
                day = "0" + day;
            }
            return year.substring(2, 4) + month + day;

        }
        return null;
    }

    /**
     * 根据Calendar日历返回String
     */
    public static String getDataString(Calendar cal) {
        return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DAY_OF_MONTH) + " " + cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND);
    }


    public static Calendar parseCalendarDate(String date) {
        Calendar d11 = new GregorianCalendar();
        Date d1 = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            d1 = sdf.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert d1 != null;
        d11.setTime(d1);
        return d11;
    }

    public static Timestamp calendar2Timestamp(Calendar cal) {
        return new Timestamp(cal.getTimeInMillis());
    }

    public static String getDatePath(Calendar cal, String pattern) {
        if (pattern == null) {
            pattern = "yyyy-MM-dd hh:mm:ss";
        }
        SimpleDateFormat sf = new SimpleDateFormat(pattern);
        return sf.format(cal.getTime());
    }

    //Date转化为Calendar
    public static Calendar date2Calendar(Date d) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        return cal;
    }

    /**
     * 日期比较是否相等
     *
     * @param type 比较方式，complete,date,
     * @return boolean
     */
    public static boolean compere(Date d1, Date d2, String type) {
        if ("date".equals(type)) {
            String pattern = "yyyy-MM-dd";
            String date1 = formatDate(d1, pattern);
            String date2 = formatDate(d2, pattern);
            return date1.equals(date2);
        } else {
            return d1.equals(d2);
        }
    }

    /**
     * 功能: 将日期对象按照某种格式进行转换，返回转换后的字符串
     *
     * @param date    日期对象
     * @param pattern 转换格式 例：yyyy-MM-dd
     */
    public static String DateToString(Date date, String pattern) {
        String strDateTime;
        SimpleDateFormat formater = new SimpleDateFormat(pattern);
        strDateTime = date == null ? null : formater.format(date);
        return strDateTime;
    }
}
