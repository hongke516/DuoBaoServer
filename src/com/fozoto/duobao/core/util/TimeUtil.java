package com.fozoto.duobao.core.util;

import com.fozoto.duobao.core.entity.Time;

import java.util.Calendar;

/**
 * Created by qingyan on 16-7-28.
 */
public class TimeUtil {

    /**
     * time.toString的格式为  2016-07-28 15:19:30
     * @return Time
     */
    public static Time getTime() {

        int year, month, day, hour, minute, second;

        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        month += 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        second = calendar.get(Calendar.SECOND);

        return new Time(year, month, day, hour, minute, second);
    }
}
