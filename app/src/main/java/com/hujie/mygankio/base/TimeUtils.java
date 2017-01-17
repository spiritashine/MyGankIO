package com.hujie.mygankio.base;

import java.util.Calendar;

/**
 * Created by hujie on 2017/1/17.
 */

public class TimeUtils {
    /**
     * 对比 “createdAt” 的时间和现在的时间
     * 1.由于要显示 “几天前”，所以要转化为int类型
     * 2.分别对比月份，年，天，返回不同的时间显示格式。
     * @param createdAt
     * @return
     */
    public static String getTime(String createdAt){
        Calendar ca = Calendar.getInstance();
        int yearNow = ca.get(Calendar.YEAR);//获取年份
        int monthNow=ca.get(Calendar.MONTH)+1;//获取月份
        int dayNow=ca.get(Calendar.DAY_OF_MONTH);//获取日
        int hourNow=ca.get(Calendar.HOUR_OF_DAY);//小时
        int minuteNow=ca.get(Calendar.MINUTE);//分

        int year=Integer.parseInt(createdAt.substring(0,4));
        int month=Integer.parseInt(createdAt.substring(5,7));
        int day=Integer.parseInt(createdAt.substring(8,10));
        int hour=Integer.parseInt(createdAt.substring(11,13));
        int minute=Integer.parseInt(createdAt.substring(14,16));

        if (month!=monthNow || year!=yearNow){
            return yearNow+"-"+monthNow+"-"+dayNow;
        }else if (month==monthNow && year==yearNow){
            if (dayNow - day > 10) {
                return yearNow + "-" + monthNow + "-" + dayNow;
            } else if (dayNow - day <=10 && dayNow - day > 2) {
                return dayNow - day+"天前";
            } else if (dayNow - day == 2) {
                return "前天";
            } else if (dayNow - day == 1) {
                return "昨天";
            } else if (day == dayNow && hourNow!=hour) {
                return hour-hourNow+"小时前";
            } else if (day == dayNow && hourNow==hour && minute!= minuteNow){
                return minute-minuteNow+"分钟前";
            } else if (day == dayNow && hourNow==hour && minute== minuteNow){
                return "刚刚";
            }
        }
        return null;
    }
}
