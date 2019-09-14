package cn.edu.bupt.yaoxintong.admin.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *获得与当前日期相隔指定天数的日期
 * Created by lhh on 2017/5/10.
 */
public class DateUtils {
    /**
     *@param d 当前日期
     * @param num 增加天数
     * @param form 日期格式
     * @return
     */
    public static String plusDays(Date d, String form,int num){
        SimpleDateFormat format = new SimpleDateFormat(form);
        format.format(d);
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DATE, num);// num为增加的天数，可以改变的
        d = ca.getTime();
        String enddate = format.format(d);
        return enddate;
    }

    public static Date plusDays(Date d, int num){
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DATE, num);// num为增加的天数，可以改变的
        d = ca.getTime();
        return d;
    }
}
