/**
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.hwc.framework.common;

import cn.freesoft.utils.FsUtils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author jzl
 *
 */
public class DateUtil {

    public static final String sdf = "yyyy-MM-dd";
    public static final String night = "23:59:59";
    public static final String patten = "yyyy-MM-dd HH:mm:ss";

    /**
     * 想要获取的日期与传入日期的差值 比如想要获取传入日期前四天的日期 day=-4即可
     * @param date
     * @param day
     * @return
     */
    public static Date getSomeDay(Date date, int day){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }

    /**
     * 获取某天的午夜时间
     * @param date
     * @param day
     * @return
     */
    public static Date getSomeDayNight(Date date, int day) {
        Date someDay = getSomeDay(date, day);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(sdf);
        String str = simpleDateFormat.format(someDay).concat(" ").concat(night);
        try {
            return new SimpleDateFormat(patten).parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 比较时间大小
     * date1 > date2  1
     * date1 < date2 -1
     * date1 = date2 0
     * @param date1
     * @param date2
     * @return
     */
    public static int compareDate(Date date1, Date date2) {
        return date1.getTime() > date2.getTime() ? 1 : date1.getTime() < date2.getTime() ? -1 : 0;
    }

	public static Long getDaySpan(Date start) {
		Long time = Long.valueOf(getInstanceOfDay(addDate(start, 1)).getTime() - start.getTime());
		time = Long.valueOf(FsUtils.divNumber(time, Integer.valueOf(1000)).longValue());
		return time;
	}
	public static Date addDate(Date date, int addnum) {
		return addDateTime(date, 5, addnum);
	}

	public static Date addDateTime(Date date, int field, int addnum) {
		if(date == null) {
			return null;
		} else {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(field, addnum);
			return c.getTime();
		}
	}

	public static Date getInstanceOfDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(11, 0);
		calendar.set(12, 0);
		calendar.set(13, 0);
		calendar.set(14, 0);
		return calendar.getTime();
	}

    public static void main(String[] args) throws ParseException {
		//System.out.print(getDayStartTime(new Date()));
		//Wed Jan 10 11:24:41 CST 2018
		//Wed Jan 10 11:24:54 CST 2018
		/*System.out.print(new Date());
		Date date1 = new Date();
		for (int i=0;i<10;i++) {
			System.out.println("i======="+i);
		}
		Date date2 = new Date();
		System.out.print(daysBetween(date1, date2));*/
		System.out.println(getInstanceOfDay(new Date()));
		System.out.println(getDaySpan(new Date()));
    }


    public static Date valueOf(String str) {
        return valueOf(str, "yyyy-MM-dd");
    }

    public static Date valueOf(String str, String dateFormatStr) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormatStr);
        ParsePosition pos = new ParsePosition(0);
        Date strtoDate = formatter.parse(str, pos);
        return strtoDate;
    }
    public static String getDate(Date date){
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日");  
		return sdf.format(date);  
    }
    public static String getAllDate(Date date){
    	SimpleDateFormat sdf=new SimpleDateFormat(patten);  
		return sdf.format(date);  
    }
    /**
     * 计算日期相隔天数
     * @param now
     * @param returnDate
     * @return
     */
    public static int daysBetween(Date now, Date returnDate) {
    	  Calendar cNow = Calendar.getInstance();
    	  Calendar cReturnDate = Calendar.getInstance();
    	  cNow.setTime(now);
    	  cReturnDate.setTime(returnDate);
    	  setTimeToMidnight(cNow);
    	  setTimeToMidnight(cReturnDate);
    	  long todayMs = cNow.getTimeInMillis();
    	  long returnMs = cReturnDate.getTimeInMillis();
    	  long intervalMs = todayMs - returnMs;
    	  return millisecondsToDays(intervalMs);
    	}
    	private static int millisecondsToDays(long intervalMs) {
    	  return (int) (intervalMs / (1000 * 86400));
    	}
    	private static void setTimeToMidnight(Calendar calendar) {
    	  calendar.set(Calendar.HOUR_OF_DAY, 0);
    	  calendar.set(Calendar.MINUTE, 0);
    	  calendar.set(Calendar.SECOND, 0);
    	}
    	
    	/*public static void main(String[] args) {
        	SimpleDateFormat df = new SimpleDateFormat(patten);
        	 String d = "2017-12-28 15:20:25";   
        	 Date returnDate = null;
             try { 
            	 returnDate = df.parse(d);
                 System.out.println(returnDate);   
             } catch (Exception e) {   
             }   
           
        	int daysBetween = daysBetween(new Date(), returnDate);
        	System.out.println(daysBetween);
    	}*/
    	/**
    	 * 获取指定时间天的开始时间
    	 * 
    	 * @param date
    	 * @return
    	 */
    	public static Date getDayStartTime(Date date) {
    		Calendar cal = Calendar.getInstance();
    		cal.setTimeInMillis(date.getTime());
    		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
    				cal.get(Calendar.DATE), 0, 0, 0);
    		return cal.getTime();
    	}

    	/**
    	 * 获取指定时间天的结束时间
    	 * 
    	 * @param date
    	 * @return
    	 */
    	public static Date getDayEndTime(Date date) {
    		Calendar cal = Calendar.getInstance();
    		cal.setTimeInMillis(date.getTime());
    		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
    				cal.get(Calendar.DATE), 23, 59, 59);
    		return cal.getTime();
    	}

}
