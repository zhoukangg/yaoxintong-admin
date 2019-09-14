package cn.edu.bupt.yaoxintong.admin.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/** 
 * @Description 系统定时任务中用到的算法 
 * @author hlz
 * @date 2016年9月23日 下午4:03:51 
 * @version V1.0
 */ 
  	
public class QuartzUtil {
	 
	/** 
	 * @Description 计算两个日期之间的相差天数
	 * @author hlz
	 * @param before
	 * @param late
	 * @return  
	 */
	  	
	public static int computeDisDays(Date start,Date end) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		//System.out.println(df.format(start));
		//System.out.println(df.format(end));
		Date beginDate = new Date();
		Date endDate = new Date();
		try {
			beginDate = df.parse(df.format(start));
			//System.out.println(beginDate);
			endDate = df.parse(df.format(end));
			//System.out.println(endDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(endDate.getTime() + " || " + beginDate.getTime());
		long i = (endDate.getTime() - beginDate.getTime())/(1000*60*60*24);
		
		return (int)i;
	}
	
	 
	/** 
	 * @Description 判断date是周几 
	 * @author hlz
	 * @param date
	 * @return  
	 */
	  	
	public static int dayForWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if(cal.get(Calendar.DAY_OF_WEEK) == 1) {
			return 7;
		} else {
			return cal.get(Calendar.DAY_OF_WEEK) - 1;
		}
		
	}
	public static void main(String args[]) {
		int i = QuartzUtil.computeDisDays(new Date(), new Date());
		System.out.println(i);
		System.out.println(QuartzUtil.dayForWeek(new Date()));
	}


	public static Date getTodayTime(Date date) {
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}


	public static boolean checkFiveFloat(List<Float> weightList) {
		// TODO Auto-generated method stub
		boolean flag = true;
		if(weightList.size() < 5) return false;
		for(int i=0; i<weightList.size()-1; i++) {
			if(weightList.get(i) > weightList.get(i+1)) {
				flag = false;
			}
		}
		return flag;
	}
}
