package cn.cashbang.core.common.utils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static final String YEAR = "year";
	public static final String MONTH = "month";
	public static final String DAY = "day";
	public static final String HOUR = "hour";
	/**  yyyyMMdd  格式 **/
	public static final String DATE_FORMAT_YYYYMMDD="yyyyMMdd";
	
	/***
	 * 根据当前还款日期和每期还款日计算下期还款日期
	 * @param curReturnDay 当前还款日期
	 * @param returnDay    每期还款日
	 * @return
	 */
	public static Date getNextMonthRepayDate(Date curReturnDay,int returnDay){
	    Calendar  calender=Calendar.getInstance();
	    calender.setTime(curReturnDay);
	    calender.set(Calendar.DAY_OF_MONTH, 1);
	    calender.add(Calendar.MONTH, 1);		
	    int maxDayOfMonth= calender.getActualMaximum(Calendar.DAY_OF_MONTH);
	    if(maxDayOfMonth>=returnDay){
	    	calender.set(Calendar.DAY_OF_MONTH, returnDay);
	     }else{
	    	calender.set(Calendar.DAY_OF_MONTH, maxDayOfMonth);
	     }
		return calender.getTime();
	}
	public static Date getNextDate(Date curReturnDay){
		Calendar  calender=Calendar.getInstance();
		calender.setTime(curReturnDay);
		calender.add(Calendar.DAY_OF_MONTH, 1);
		return calender.getTime();
	}

	public static ArrayList<String> getDateList(){

		ArrayList<String>  dateList = new ArrayList<String>();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		
		for (int k =0;k<7;k ++){
			Calendar  calender=Calendar.getInstance();
			calender.setTime(new Date());
			calender.add(Calendar.DAY_OF_MONTH, 7+k);

            String week = "";
            int weekday = calender.get(Calendar.DAY_OF_WEEK);
            if (weekday == 1) {
                week = "周日";
            } else if (weekday == 2) {
                week = "周一";
            } else if (weekday == 3) {
                week = "周二";
            } else if (weekday == 4) {
                week = "周三";
            } else if (weekday == 5) {
                week = "周四";
            } else if (weekday == 6) {
                week = "周五";
            } else if (weekday == 7) {
                week = "周六";
            }
			dateList.add(sf.format(calender.getTime())+","+week);

		}
		return dateList;
	}

    private String getWeek() {
        String week = "";
        Date today = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        int weekday = c.get(Calendar.DAY_OF_WEEK);
        if (weekday == 1) {
            week = "周日";
        } else if (weekday == 2) {
            week = "周一";
        } else if (weekday == 3) {
            week = "周二";
        } else if (weekday == 4) {
            week = "周三";
        } else if (weekday == 5) {
            week = "周四";
        } else if (weekday == 6) {
            week = "周五";
        } else if (weekday == 7) {
            week = "周六";
        }
        return week;
    }
	
	/***
	 * 根据当前还款日期和每期还款日计算最后还款日期
	 * @param beginReturnDay 还款起始日期
	 * @param term         借款期限
	 * @param returnDay    每期还款日
	 * @return
	 */
	public static Date getLastMonthRepayDate(Date beginReturnDay,int term,int returnDay){
	    Calendar  calender=Calendar.getInstance();
	    calender.setTime(beginReturnDay);
	    calender.set(Calendar.DAY_OF_MONTH, 1);
	    calender.add(Calendar.MONTH, term-1);		
	    int maxDayOfMonth= calender.getActualMaximum(Calendar.DAY_OF_MONTH);
	    if(maxDayOfMonth>=returnDay){
	    	calender.set(Calendar.DAY_OF_MONTH, returnDay);
	     }else{
	    	calender.set(Calendar.DAY_OF_MONTH, maxDayOfMonth);
	     }
		return calender.getTime();
	}
	
	
	
	public static Date getPriorMonthRepayDate(Date curReturnDay,int returnDay){
	    Calendar  calender=Calendar.getInstance();
	    calender.setTime(curReturnDay);
	    calender.set(Calendar.DAY_OF_MONTH, 1);
	    calender.add(Calendar.MONTH, -1);		
	    int maxDayOfMonth= calender.getActualMaximum(Calendar.DAY_OF_MONTH);
	    if(maxDayOfMonth>=returnDay){
	    	calender.set(Calendar.DAY_OF_MONTH, returnDay);
	     }else{
	    	calender.set(Calendar.DAY_OF_MONTH, maxDayOfMonth);
	     }
		return calender.getTime();
	}	
	public static Date addDateTime(Date curReturnDay,int Calendar_Date_Type,int amount){
	    Calendar  calender=Calendar.getInstance();
	    calender.setTime(curReturnDay);
	    calender.add(Calendar_Date_Type, amount);	    
		return calender.getTime();
	}	
	

	public static String formatterDate(Date date,String formater){
		SimpleDateFormat sdf=new SimpleDateFormat(formater);
		return sdf.format(date);
	}
	
	public static Date parseDate(Date date,String formater){
		SimpleDateFormat sdf=new SimpleDateFormat(formater);
		try {
			return sdf.parse(sdf.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
		
	}
	/**
	 * 给定日期和标志，返回 对应字符串  “年”  “月”  “日”
	 * @param date   
	 * @param flag  YEAR  MONTH  DAY 
	 * @return	“年”  “月”  “日”
	 */
	public static String getSplitTime(Date date,String flag){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		String split = "";
		if(YEAR.equals(flag)){
			split =  new Integer(cal.get(Calendar.YEAR)).toString();
		}else if(MONTH.equals(flag)){
			split = new Integer(cal.get(Calendar.MONTH)+1).toString();
		}else if(DAY.equals(flag)){
			split = new Integer(cal.get(Calendar.DAY_OF_MONTH)).toString();
		}else if(HOUR.equals(flag)){
			split =new Integer(cal.get(Calendar.HOUR_OF_DAY)).toString();
		}
		return split;
	}
	/**
	 * 给传入的一个时间对象设置小时 、 分钟、 秒的值
	 * 如果传入的参数值过大，则返回     23:59:59
	 * 如果传入的参数值过小，则返回     00:00:00
	 * @param date   需要返回的时间
	 * @param hour    小时   24小时制
	 * @param minute   分钟
	 * @param second   秒 
	 * @return
	 */
	public static Date getFullDate(Date date,int hour,int minute,int second){
		Calendar cd = Calendar.getInstance();
		cd.setTime(date);
		if(hour>23){
			hour=23;
		}else if(hour<0){
			hour=0;
		}
		cd.set(Calendar.HOUR_OF_DAY, hour);
		
		if(minute>59){
			minute = 59;
		}else if(minute<0){
			minute = 0;
		}
		cd.set(Calendar.MINUTE,minute);
		
		if(second>59){
			second=59;
		}else if(second<0){
			second=0;
		}
		cd.set(Calendar.SECOND, minute);
		
		return cd.getTime();
	}
	/**
	 * 
	 * @param date
	 * @return  获取某一天的最大时间
	 */
	public static Date getMaxDateOfDay(Date date){
		return getFullDate(date,23,59,59);
	}
	/**
	 * 
	 * @param date
	 * @return 获取某一天的最小时间
	 */
	public static Date getMinDateOfDay(Date date){
		return getFullDate(date,0,0,0);
	}
	/**
	 * 两个日期相减，得到差值天数
	 * @param minus		被减数
	 * @param Minuend	减数
	 * @return
	 */
	public static int minusDate2Day(Date minus,Date Minuend){
		Calendar minusCal=Calendar.getInstance();
		Calendar minuendCal=Calendar.getInstance();
		minusCal.setTime(getMinDateOfDay(minus));
		minuendCal.setTime(getMinDateOfDay(Minuend));
		
		long minusInMillis = minusCal.getTimeInMillis();
		long MinuendInMillis = minuendCal.getTimeInMillis();
		
		return (int) ((minusInMillis-MinuendInMillis)/(1000*60*60*24));
	}


	/**
	 * 两个日期相减，得到差值天数
	 * @param minus		被减数
	 * @param Minuend	减数
	 * @return
	 */
	public static int minusDate2Day(String minus,String Minuend) throws Exception{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar minusCal=Calendar.getInstance();
		Calendar minuendCal=Calendar.getInstance();
		minusCal.setTime(getMinDateOfDay(simpleDateFormat.parse(minus)));
		minuendCal.setTime(getMinDateOfDay(simpleDateFormat.parse(Minuend)));

		long minusInMillis = minusCal.getTimeInMillis();
		long MinuendInMillis = minuendCal.getTimeInMillis();

		return (int) ((minusInMillis-MinuendInMillis)/(1000*60*60*24));
	}
	//两个日期差（单位:年 365天）
	public static Integer getYearsBetween(Date beginDate,Date endDate){
		if(endDate.after(beginDate)){
			BigDecimal beginTime=new BigDecimal(beginDate.getTime());
			BigDecimal endTime=new BigDecimal(endDate.getTime());
			BigDecimal year=new BigDecimal(1000*60*60*24*365l);
			BigDecimal result=endTime.subtract(beginTime).divide(year,1, BigDecimal.ROUND_HALF_UP);
			return result.intValue();
		}
		return 0;
	}
	//两个日期差（单位:月30天）
	public static Integer getMonthsBetween(Date beginDate,Date endDate){
		if(endDate.after(beginDate)){
			BigDecimal beginTime=new BigDecimal(beginDate.getTime());
			BigDecimal endTime=new BigDecimal(endDate.getTime());
			BigDecimal month=new BigDecimal(1000*60*60*24*30l);
			BigDecimal result=endTime.subtract(beginTime).divide(month,0, BigDecimal.ROUND_HALF_EVEN);
			return result.intValue();
		}
		return 0;
	}
	
	public static Date getDateFromString(String date){
		return getDateFromString(date, "yyyy-MM-dd");
	}
	public static Date getDateFromString(String date,String format){
		SimpleDateFormat sdf =   new SimpleDateFormat(format);
		Date result=null;
		try {
			result=sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String getCurrentTime(){
		Date currentDate=new Date();
		return formatterDate(currentDate, "yyyyMMddHHmmss");
	}
	
	public static String getCurrentTimeYMDHMS(){
		Date currentDate=new Date();
		return formatterDate(currentDate, "yyyy-MM-dd HH:mm:ss");
	}
	
	public static Date getNextMonth(Date date){
		Calendar  calender=Calendar.getInstance();
		calender.setTime(date);
		calender.add(Calendar.MONTH, 1);
		return calender.getTime();
	}
	
	/**
	 * 
	 * 方法功能描述：获取指定月账单的起始日期<br>
	 * @author sunpeng
	 * @param billDateStr 201605
	 * @return 2016-05-01
	 */
	public static String getMonthFirstDate(String billDateStr){
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMM");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(sf.parse(billDateStr));
			c.add(Calendar.MONTH, 0);
			c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String first = format.format(c.getTime());
		return first;
	}
	
	/**
	 * 
	 * 方法功能描述：获取指定日期的月份的起始日期<br>
	 * @author sunpeng
	 * @param date 
	 * @return 2016-05-01
	 */
	public static Date getMonthFirstDate(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		return c.getTime();
	}
	
	/**
	 * 
	 * 方法功能描述：获取指定月账单的最终日期<br>
	 * @author sunpeng
	 * @param billDateStr 201605
	 * @return 2016-05-31
	 */
	public static String getMonthLastDate(String billDateStr){
		   SimpleDateFormat sf = new SimpleDateFormat("yyyyMM");
		   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	        // 获取当前月最后一天
			Calendar ca = Calendar.getInstance();
			try {
				ca.setTime(sf.parse(billDateStr));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
			String last = format.format(ca.getTime());
			return last;
	}
	
	/**
	 * 
	 * 方法功能描述：获取指定日期的月份的最终日期<br>
	 * @author sunpeng
	 * @param date 
	 * @return 2016-05-31
	 */
	public static Date getMonthLastDate(Date date){
	        // 获取当前月最后一天
			Calendar ca = Calendar.getInstance();
			ca.setTime(date);
			ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
			return ca.getTime();
	}
	
	/**
	 * 
	 * 方法功能描述：一个时间段  是否包含 targetDate， 等于也属于包含<br>
	 * @author zhouwy
	 * @version since 2016年5月28日 
	
	 * 修改记录：<br>
	 * <ul>    
	 * <li>修改记录描述1
	 * @author zhouwy
	 * @version since 2016年5月28日 
	 *<br>
	 * </ul>
	 * @param targetDate
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static boolean isContain(Date targetDate, Date startDate,Date endDate){
		if(targetDate.equals(startDate)||targetDate.equals(endDate))
			return true;
		if(targetDate.after(startDate) && targetDate.before(endDate))
			return true;
		return false;
	}

	// 比较两个时间大小
	public static boolean isBigDate(Date targetDate, Date paramDate){

		boolean flag = targetDate.before(paramDate);
		return flag;
	}

	public static void main(String[] args) {
		ArrayList a = DateUtil.getDateList();
		System.out.println(a);
	}
}
