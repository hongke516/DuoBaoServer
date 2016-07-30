package com.fozoto.duobao.core.entity;

/**
 * Created by qingyan on 16-7-28.
 */
public class Time {

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;

    public Time() {
    }

    public Time(int year, int month, int day, int hour, int minute, int second) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return year+"-"+parseTime(month)+"-"+parseTime(day)+" "+parseTime(hour)+":"+parseTime(minute)+":"+parseTime(second);
    }

    // 解决在int下0在第一位会被忽略的问题
    private String parseTime(int time) {
        if (time<10) {
            return  "0"+time;
        }
        return time+"";
    }
}
