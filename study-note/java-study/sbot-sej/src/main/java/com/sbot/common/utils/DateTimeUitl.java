package com.sbot.common.utils;

import com.sbot.common.exception.ProjectException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/12/3
 */
public class DateTimeUitl {

    /**
     * 获取日期中的年、月、日、等信息
     * use eg: getFieldFromDate(new Date(), Calendar.YEAR)
     */
    public static int getFieldFromDate(Date date, int calendarField) throws ProjectException {
        if (calendarField < 1 || calendarField > 7) throw new ProjectException("获取日期时间信息格式错误");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(calendarField);
    }

    public static String date2String(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        String dateStr = dateFormat.format(date);
        return dateStr;
    }

    public static Date string2Date(String dateStr, String format) throws ProjectException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date date;
        try {
            date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            throw new ProjectException("日期格式错误");
        }
        return date;
    }

    public static String randomID(int length14Add, int x) {
        final Random random = new Random();
        final byte[] charBytes = new byte[]{
                'A', 'a'
        };
        final byte[] numberBytes = new byte[]{
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9
        };
        StringBuilder id = new StringBuilder();
        String timeStr = date2String(new Date(), "yyyyMMddhhmmss");
        id.append(timeStr);
        id.append((char)charBytes[random.nextInt(charBytes.length)]);
        for (int i = 0 ; i < length14Add; i++) {
            if(i == x)
                id.append((char)charBytes[random.nextInt(charBytes.length)]);
            else id.append(numberBytes[random.nextInt(numberBytes.length)]);
        }
        id.append((char)charBytes[random.nextInt(charBytes.length)]);

        return id.toString();
    }
}
