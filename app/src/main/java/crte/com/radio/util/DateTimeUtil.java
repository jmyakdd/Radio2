package crte.com.radio.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by CRTE-CD-13 on 2017/2/25.
 */
public class DateTimeUtil {
    public static String getTimeOfFileStr(long timeStamp) {
        return formatData("yyyyMMddHHmmss", timeStamp);
    }

    public static String getTimeOfStr(long timeStamp) {
        return formatData("yyyy-MM-dd HH:mm:ss", timeStamp);
    }

    public static String getTimeOfDate(long timeStamp) {
        return formatData("yyyy-MM-dd", timeStamp);
    }

    public static String formatData(String dataFormat, long timeStamp) {
        if (timeStamp == 0) {
            return "";
        }
        String result = "";
        SimpleDateFormat format = new SimpleDateFormat(dataFormat);
        result = format.format(new Date(timeStamp));
        return result;
    }

    public static int getTimeOfInt(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd HH:mm",//:ss
                Locale.CHINA);
        Date date;
        int l = -1;
        try {
            date = sdr.parse(time);
            l = (int) (date.getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }

    /**
     * 获取String类型时间的时间戳
     *
     * @param time
     * @return
     */
    public static long getTimeOfLong(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        Date date;
        long l = -1;
        try {
            date = sdr.parse(time);
            l = date.getTime()/1000;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }
    /**
     * 获取String类型时间的时间戳
     *
     * @param time
     * @return
     */
    public static long getTimeOfLong2(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        Date date;
        long l = -1;
        try {
            date = sdr.parse(time);
            l = date.getTime()/1000;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }
    /**
     * 获取两个String类型的时间间隔秒数
     *
     * @param timeStr1
     * @param timeStr2
     * @return
     */
    public static long getTimeIntervalOfSecond(String timeStr1, String timeStr2) {
        if (timeStr1 == null || timeStr2 == null) {
            return 0;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        long date1 = 0;
        long date2 = 0;
        try {
            date1 = sdf.parse(timeStr1).getTime();
            date2 = sdf.parse(timeStr2).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Math.abs(date1 - date2) / 1000;
    }

    /**
     * 获取两个时间戳的间隔秒数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static long getTimeIntervalOfSecond(long date1, long date2) {
        return Math.abs(date2 - date1);
    }
}
