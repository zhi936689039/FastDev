package com.mvp.mvpmodule.util;

import android.annotation.SuppressLint;
import android.content.Context;

import com.mvp.mvpmodule.BuildConfig;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author 创建人 ：ouyangzhibao
 * @version 1.0
 * @package 包名 ：com.xdp.client.util
 * @createTime 创建时间 ：2019/7/11
 * @modifyBy 修改人 ：ouyangzhibao
 * @modifyTime 修改时间 ：2019/7/11
 * @modifyMemo 修改备注：
 */
public class TimeUtil {
    /**
     * 获取两个时间节点之间的月份列表
     **/
    public static List<String> getMonthBetween(String minDate, String maxDate) {
        ArrayList<String> result = new ArrayList<String>();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月

            Calendar min = Calendar.getInstance();
            Calendar max = Calendar.getInstance();
            min.setTime(sdf.parse(minDate));
            min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

            max.setTime(sdf.parse(maxDate));
            max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

            Calendar curr = min;
            while (curr.before(max)) {
                result.add(sdf.format(curr.getTime()));
                curr.add(Calendar.MONTH, 1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 时间加0
     *
     * @param stamp
     * @param format
     * @return
     */
    public static String stampToTime(String stamp, String format) {
        String sd = "";
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sd = sdf.format(new Date(Long.parseLong(stamp))); // 时间戳转换日期
        return sd;
    }

    // 获得本月第一天
    @SuppressLint("WrongConstant")
    public static String getTimesMonthmorning() {
        String createTime_begin;
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));

        Date date = cal.getTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH) + 1;
        if (month < 10) {
            createTime_begin = calendar.get(Calendar.YEAR) + "-0" + month + "-0" + calendar.get(Calendar.DAY_OF_MONTH);
        } else {
            createTime_begin = calendar.get(Calendar.YEAR) + "-" + month + "-0" + calendar.get(Calendar.DAY_OF_MONTH);
        }
        return createTime_begin;
    }

    // 获得本月最后一天
    @SuppressLint("WrongConstant")
    public static String getTimesMonthnight() {
        String createTime_end;
        String monthString;
        String dayString;
        //获取当前月最后一天
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date date = ca.getTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH) + 1;
        if (month < 10) {
            monthString = "-0" + month;
        } else {
            monthString = "-" + month;
        }
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        if (day < 10) {
            dayString = "-0" + day;
        } else {
            dayString = "-" + day;
        }
        createTime_end = calendar.get(Calendar.YEAR) + monthString + dayString;

        return createTime_end;
    }

    /**
     * 根据提供的年月日获取该月份的第一天
     *
     * @param year
     * @param monthOfYear
     * @return
     * @Description: (这里用一句话描述这个方法的作用)
     * @Author: gyz
     * @Since: 2017-1-9下午2:26:57
     */
    public static String getSupportBeginDayofMonth(int year, int monthOfYear) {
        String createTime_begin;
        Calendar cal = Calendar.getInstance();
        // 不加下面2行，就是取当前时间前一个月的第一天及最后一天
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, monthOfYear);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        cal.add(Calendar.DAY_OF_MONTH, -1);
        Date lastDate = cal.getTime();

        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDate = cal.getTime();


        Calendar calendar = Calendar.getInstance();
        calendar.setTime(firstDate);
        int month = calendar.get(Calendar.MONTH) + 1;
        if (month < 10) {
            createTime_begin = calendar.get(Calendar.YEAR) + "-0" + month + "-0" + calendar.get(Calendar.DAY_OF_MONTH);
        } else {
            createTime_begin = calendar.get(Calendar.YEAR) + "-" + month + "-0" + calendar.get(Calendar.DAY_OF_MONTH);
        }

        return createTime_begin;
    }

    /**
     * 根据提供的年月获取该月份的最后一天
     *
     * @param year
     * @param monthOfYear
     * @return
     * @Description: (这里用一句话描述这个方法的作用)
     * @Author: gyz
     * @Since: 2017-1-9下午2:29:38
     */
    public static String getSupportEndDayofMonth(int year, int monthOfYear) {
        String createTime_end;
        String monthString;
        String dayString;
        Calendar cal = Calendar.getInstance();
        // 不加下面2行，就是取当前时间前一个月的第一天及最后一天
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, monthOfYear);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);

        cal.add(Calendar.DAY_OF_MONTH, -1);
        Date lastDate = cal.getTime();


        Calendar calendar = Calendar.getInstance();
        calendar.setTime(lastDate);
        int month = calendar.get(Calendar.MONTH) + 1;
        if (month < 10) {
            monthString = "-0" + month;
        } else {
            monthString = "-" + month;
        }
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        if (day < 10) {
            dayString = "-0" + day;
        } else {
            dayString = "-" + day;
        }
        createTime_end = calendar.get(Calendar.YEAR) + monthString + dayString;
        return createTime_end;
    }


    /**
     * 计算时差 根据 long 返回时间点
     *
     * @param millisecond
     * @return string 0天0时11分55秒
     */
    public static String parseMillisecone(long millisecond) {
        String time = null;
        try {
            long yushu_day = millisecond % (1000 * 60 * 60 * 24);
            long yushu_hour = (millisecond % (1000 * 60 * 60 * 24))
                    % (1000 * 60 * 60);
            long yushu_minute = millisecond % (1000 * 60 * 60 * 24)
                    % (1000 * 60 * 60) % (1000 * 60);
            @SuppressWarnings("unused")
            long yushu_second = millisecond % (1000 * 60 * 60 * 24)
                    % (1000 * 60 * 60) % (1000 * 60) % 1000;
            if (yushu_day == 0) {
                return (millisecond / (1000 * 60 * 60 * 24)) + "天";
            } else {
                if (yushu_hour == 0) {
                    return (millisecond / (1000 * 60 * 60 * 24)) + "天"
                            + (yushu_day / (1000 * 60 * 60)) + "时";
                } else {
                    if (yushu_minute == 0) {
                        return (/*millisecond / (1000 * 60 * 60 * 24)) + "天"
                                + (yushu_day / (1000 * 60 * 60)) + "时"
                                + */(yushu_hour / (1000 * 60)) + "分钟")   + (yushu_minute / 1000) + "秒";
                    } else {
                        return (yushu_hour / (1000 * 60)) + "分钟"
                                   + (yushu_minute / 1000) + "秒";

                    }

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }

    /**
     * 获取给定时间与当前系统时间的差值（以毫秒为单位）
     *
     * @author GaoHuanjie
     */
    public long getTimeDifferenceBetweenSystemTimeAndParamTime(String paramTime) {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String systemTime = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss").format(new Date().getTime());// 获取系统时间
        long difference = 0;
        try {
            Date systemDate = dateFormat.parse(systemTime);
            Date paramDate = dateFormat.parse(paramTime);
            difference = systemDate.getTime() - paramDate.getTime();
            LogUtil.e("系统时间：" , systemTime + "，给定时间：" + paramTime 	+ "，给定时间与当前系统时间的差值（以毫秒为单位）：" + difference + "毫秒", BuildConfig.DEBUG);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return difference;
    }
    /**
     * 将毫秒转时分秒
     *
     * @param time
     * @return
     */
    public static String generateTime(long time) {
        int totalSeconds = (int) (time / 1000);
        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;
        int day = hours / 24;
        if(minutes==0){
            //秒
            return String.format("%2d秒",seconds);
        }else{
            return hours > 0 ? String.format("%02d天%02d时%02d分%02d秒", day, hours, minutes, seconds) : String.format("%2d分钟", minutes, seconds);
        }
    }

    /**
     * 秒转分钟
     * @param time
     * @return
     */
    public static String formattime(long time) {
        String min = (time / ( 60)) + "";
        String second = (time % (60)) + "";
        if (min.length() < 2) {
            min = 0 + min;
        }
        if (second.length() < 2) {
            second = 0 + second;
        }
        return min + ":" + second;
    }

    /**
     * 毫秒转秒
     * @param time
     * @return
     */
    public static String formattime2(long time) {
        double k=Math.round(time/1000L);
        double result=(int)Math.round(k);
        String resultString=new BigDecimal(result+"").toString();
     /*   if(resultString.contains(".")){
            return resultString.substring(0,resultString.indexOf("."));
        }else{
            return resultString;
        }*/
     return resultString;
    }


    /**
     * 判断2个时间大小
     * yyyy-MM-dd HH:mm 格式（自己可以修改成想要的时间格式）
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static int timeCompare(String startTime, String endTime) {
        int i = 0;
        //注意：传过来的时间格式必须要和这里填入的时间格式相同
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date1 = dateFormat.parse(startTime);//开始时间
            Date date2 = dateFormat.parse(endTime);//结束时间
            // 1 结束时间小于开始时间 2 开始时间与结束时间相同 3 结束时间大于开始时间
            if (date2.getTime() < date1.getTime()) {
                //结束时间小于开始时间
                i = 1;
            } else if (date2.getTime() == date1.getTime()) {
                //开始时间与结束时间相同
                i = 2;
            } else if (date2.getTime() > date1.getTime()) {
                //结束时间大于开始时间
                i = 3;
            }
        } catch (Exception e) {

        }
        return i;
    }
    /**
     * 将时间戳转换成自定义的时间格式
     */
    public static String getFormatDate(Context context, long milliseconds, String format) {
        SimpleDateFormat sFormat;
        sFormat = new SimpleDateFormat(format, context.getResources().getConfiguration().locale);
        sFormat.setTimeZone(TimeZone.getDefault());
        return sFormat.format(new Date(milliseconds));
    }


    /**
     * 从字符串中获取日期
     *
     * @param strDate
     * @return
     */
    public static String getDateFromTimeString(String strDate) {
        String s = "";
        Pattern p = Pattern.compile("(\\d{4})-(\\d{1,2})-(\\d{1,2})");
        Matcher m = p.matcher(strDate);
        while (m.find()) {
            s += m.group();
        }
        return s;
    }

    /**
     * 从字符串中获取时间
     *
     * @param strDate
     * @return
     */
    public static String getTimeFromTimeString(String strDate) {
        String s = "";
        Pattern p = Pattern.compile("(\\d{1,2}):(\\d{1,2}):(\\d{1,2})");
        Matcher m = p.matcher(strDate);
        while (m.find()) {
            s += m.group();
        }
        return s;
    }

    /**
     * 毫秒时间戳转换时间字符串
     *
     * @param trackTime
     * @param format
     * @return
     */
    public static String formateTrackTime(double trackTime, String format) {
        String result = new SimpleDateFormat(format).format(trackTime);
//        System.out.println("13位数的时间戳（毫秒）--->Date:" + result);
        return result;
    }

    /**
     * 秒级时间戳转为时间字符串
     *
     * @param time
     * @param pattern
     * @return
     */
    public static String getDate2String(long time, String pattern) {
        Date date = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.getDefault());
        return format.format(date);
    }
    public static int getCurrentYear(){
        Calendar cd = Calendar.getInstance();
        int currentTime=cd.get(Calendar.YEAR);
        return currentTime;
    }
    // 将字符串转为时间戳
    public static Date  getTime(String user_time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return dateFormat.parse(user_time);
        } catch (ParseException e) {
            return null;
        }
    }


}
