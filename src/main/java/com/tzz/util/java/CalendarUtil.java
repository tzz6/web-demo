package com.tzz.util.java;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.junit.Test;

public class CalendarUtil {

	/** 获取年、月、日、时、分、秒、毫秒 */
	public static String getCurrYMDHMSM() {
		Calendar calendar = new GregorianCalendar();// 子类实例化
		// 获取年、月、日、时、分、秒、毫秒
		int currYEAR = calendar.get(Calendar.YEAR);
		int currMONTH = calendar.get(Calendar.MONTH) + 1;
		int currDAY = calendar.get(Calendar.DAY_OF_MONTH);
		int currHOUR = calendar.get(Calendar.HOUR_OF_DAY);
		int currMINUTE = calendar.get(Calendar.MINUTE);
		int currSECOND = calendar.get(Calendar.SECOND);
		int currMILLISECOND = calendar.get(Calendar.MILLISECOND);
		System.out.println("年： " + currYEAR);
		System.out.println("月 " + currMONTH);
		System.out.println("日： " + currDAY);
		System.out.println("时： " + currHOUR);
		System.out.println("分： " + currMINUTE);
		System.out.println("秒： " + currSECOND);
		System.out.println("毫秒 " + currMILLISECOND);
		return currYEAR + "-" + currMONTH + "-" + currDAY + " " + currHOUR + ":" + currMINUTE + ":" + currSECOND + ":"
				+ currMILLISECOND;
	}

	/** 当前月第一天 */
	public static String getCurrMonthFirstDay() {
		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		Date firstTime = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(firstTime) + " 00:00:00";
	}

	/** 当前月最后一天 */
	public static String getCurrMonthLastDay() {
		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.DATE, 1);
		calendar.roll(Calendar.DATE, -1);
		Date lastTime = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(lastTime) + " 23:59:59";
	}

	/** 指定年月第一天 */
	public static String getMonthFirstDay(int year, int moth) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, moth - 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		Date firstTime = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(firstTime) + " 00:00:00";

	}

	/** 指定年月最后一天 */
	public static String getMonthLastDay(int year, int moth) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, moth - 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.DATE, 1);
		calendar.roll(Calendar.DATE, -1);
		Date lastTime = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(lastTime) + " 23:59:59";
	}

	// 根据日期取得星期几
	public static String getWeekArr(Date date) {
		String[] weeks = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (week_index < 0) {
			week_index = 0;
		}
		return weeks[week_index];
	}

	// 根据日期取得星期几
	public static String getWeek(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE", Locale.CHINESE);
		return sdf.format(date);
	}

	@Test
	public void test() {
		try {
			Calendar cal = Calendar.getInstance();

			// 当前时间的前一天
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			String tmStr = "2015-5-18";
			Date d = sdf2.parse(tmStr);
			System.out.println(d);
			Calendar now = Calendar.getInstance();
			now.setTime(d);
			now.set(Calendar.HOUR, now.get(Calendar.HOUR) - 1);
			System.out.println(sdf2.format(now.getTime()));

			// 最近N天
			SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			cal.add(Calendar.DATE, -7);
			System.out.println("最近7天" + df2.format(cal.getTime()));
			Calendar ca2 = Calendar.getInstance();
			// 最近N个月
			ca2.add(Calendar.MONTH, -1);// 最近一个月
			System.out.println("最近一个月" + df2.format(ca2.getTime()));
			Calendar ca3 = Calendar.getInstance();
			// 最近N个年
			ca3.add(Calendar.YEAR, -1);// 最近一年
			System.out.println("最近一年" + df2.format(ca3.getTime()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
