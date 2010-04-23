package kk.leech.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * @author Administrator
 *
 */
public class DateUtil {

	public static final String PATTERN_YYMMDD = "yyMMdd";
	public static final String PATTERN_YYYYMMDD = "yyyyMMdd";
	public static final String PATTERN_YYYY_MM = "yyyy-MM";
	public static final String PATTERN_YYYY_MM_DD = "yyyy-MM-dd";
	public static final String PATTERN_YY_MM_DD = "yy-MM-dd";
	public static final String PATTERN_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public static final String PATTERN_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	public static final String PATTERN_YYYY_MM_DD_DISPLAY = "yyyy/MM/dd";
	public static final String PATTERN_YYYY_MM_DD_HH_MM_SS_DASH = "yyyy-MM-dd HH:mm:ss";
	public static final String PATTERN_YYYY_MM_DD_HH_MM_SS_SLASH = "yyyy/MM/dd HH:mm:ss";
	public static final String PATTERN_ORACLE = "yyyy-mm-dd hh24:mi:ss"; //oracle 15-1月-07

	/**
	 * Get first day of current month
	 * @return
	 */
	public static Date getFirstDayOfCurrentMonth() {

		Calendar now = now();
		now.set(Calendar.DATE, 1);
		return now.getTime();
	}

	/**
	 * Format date by pattern
	 * @param date 
	 * @param pattern  format type
	 * @return
	 */
	public static String dateFormater(Date date, String pattern) {

		String formattedDate = "";
		if (date != null) {
			Calendar calendar = GregorianCalendar.getInstance();
			calendar.setTime(date);
			if (pattern.equals(PATTERN_ORACLE)) { //to_date('2007-12-15', YYYY-MM-DD)
				formattedDate = "to_date('"
						+ calendarFormater(calendar,
								PATTERN_YYYY_MM_DD_HH_MM_SS) + "', '"
						+ PATTERN_ORACLE + "')";
			} else {
				formattedDate = calendarFormater(calendar, pattern);
			}
		}
		return formattedDate;
	}

	/**
	 * Format date by pattern
	 * @param date
	 * @param pattern  format type
	 * @return
	 */
	public static String calendarFormater(Calendar date, String pattern) {

		String formattedDate = "";
		if (date != null) {
			if (pattern.equals(PATTERN_ORACLE)) { //to_date('2007-12-15', YYYY-MM-DD)
				formattedDate = "to_date('"
						+ calendarFormater(date, PATTERN_YYYY_MM_DD_HH_MM_SS)
						+ "', '" + PATTERN_ORACLE + "')";
			} else {
				SimpleDateFormat formatter = new SimpleDateFormat(pattern,
						Locale.US);
				formattedDate = formatter.format(date.getTime());
			}
		}
		return formattedDate;
	}

	/**
	 * @return
	 */
	public static Calendar now() {

		return GregorianCalendar.getInstance(Locale.TAIWAN);
	}

	/**
	 * Set year, month and day
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static Calendar getDate(int year, int month, int day) {

		month = month - 1; //month is 0 based
		return new GregorianCalendar(year, month, day);
	}

	/**
	 * @return
	 */
	public static String getChineseDate() {

		StringBuffer date = new StringBuffer();
		Calendar calendar = GregorianCalendar.getInstance();
		String y = CommonUtil.makeUpZero(String.valueOf(calendar
				.get(Calendar.YEAR) - 1911), 3);
		String m = CommonUtil.makeUpZero(String.valueOf(calendar
				.get(Calendar.MONTH) + 1), 2);
		String d = CommonUtil.makeUpZero(String.valueOf(calendar
				.get(Calendar.DAY_OF_MONTH)), 2);
		date.append(y).append(m).append(d);
		return date.toString();
	}

	/**
	 * @return
	 */
	public static String getChineseDateWithoutDay() {

		String dateString = getChineseDate();
		return dateString.substring(0, dateString.length() - 2);
	}

	/**
	 * If date is null, use current time instead automatically
	 */
	public static Date addDate(Date date, int day) {

		Calendar calendar = GregorianCalendar.getInstance();
		if (date != null)
			calendar.setTime(date);
		calendar.add(Calendar.DATE, day);
		return calendar.getTime();
	}

	public static long checkGap(int type, Date startDate, Date endDate) {

		long gap = 0;
		Calendar start = new GregorianCalendar();
		start.setTime(startDate);
		Calendar end = new GregorianCalendar();
		end.setTime(endDate);

		//	  long millis = start.getTimeInMillis() - end.getTimeInMillis();
		BigDecimal millis = new BigDecimal(start.getTimeInMillis()
				- end.getTimeInMillis());
		switch (type) {
		case Calendar.DATE:
			//	    gap = millis / (1000 * 60 * 60 * 24);
			gap = millis.divide(new BigDecimal(1000 * 60 * 60 * 24),
					BigDecimal.ROUND_DOWN).longValue();
			break;
		case Calendar.MONTH:
			//	    gap = millis / (1000 * 60 * 60 * 24 * 30);
			gap = millis.divide(new BigDecimal(1000 * 60 * 60 * 24 * 30),
					BigDecimal.ROUND_CEILING).longValue();
			break;
		case Calendar.YEAR:
			//	    gap = millis / (1000 * 60 * 60 * 24 * 30 * 365);
			gap = millis.divide(new BigDecimal(1000 * 60 * 60 * 24 * 30 * 365),
					BigDecimal.ROUND_DOWN).longValue();
			break;
		default:
			throw new UnsupportedOperationException(
					"Count year, month, date only.");
		}

		if (gap < 0)
			gap = 0 - gap;

		return gap;
	}

	/**
	 *
	 * @param birthDay
	 * @return
	 */
	public static int calculateAge(Date birthDay) {
		Calendar birth_c = Calendar.getInstance();
		birth_c.setTime(birthDay);

		Date today = new Date(System.currentTimeMillis());
		Calendar c2 = Calendar.getInstance();
		c2.setTime(today);

		long remaindar = c2.getTimeInMillis() - birth_c.getTimeInMillis();

		long aYear = (long) 1000 * (long) 60 * (long) 60 * (long) 24
				* (long) 365;

		int age = (int) (remaindar / aYear);
		return age;
	}

	public static final void main(String[] args) {

		Date d1 = DateUtil.getDate(2005, 1, 13).getTime();
		Date d2 = DateUtil.getDate(2006, 5, 1).getTime();

		System.out.println(DateUtil.calendarFormater(Calendar.getInstance(),
				DateUtil.PATTERN_ORACLE));
	}

	/**
	 * Parse String to Date by pattern
	 * @param str
	 * @param pattern 
	 * @return
	 */
	public static Date parseDate(String str, String pattern) {

		Date formattedDate = null;
		if (str != null && !str.equals("")) {
			SimpleDateFormat formatter = new SimpleDateFormat(pattern,
					Locale.US);
			try {
				formattedDate = formatter.parse(str);
			} catch (ParseException e) {
				LogUtil.writeLog(e.toString());
			}
		}
		return formattedDate;
	}
}
