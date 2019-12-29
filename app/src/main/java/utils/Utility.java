package utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.content.SharedPreferences;

public class Utility {

	public Utility() {
		// TODO Auto-generated constructor stub
	}

	// 日期的转化方法

	public static Date formatDate2(String dateStr) {
		String[] aStrings = dateStr.split(" ");
		// 5
		if (aStrings[1].equals("Jan")) {
			aStrings[1] = "01";
		}
		if (aStrings[1].equals("Feb")) {
			aStrings[1] = "02";
		}
		if (aStrings[1].equals("Mar")) {
			aStrings[1] = "03";
		}
		if (aStrings[1].equals("Apr")) {
			aStrings[1] = "04";
		}
		if (aStrings[1].equals("May")) {
			aStrings[1] = "05";
		}
		if (aStrings[1].equals("Jun")) {
			aStrings[1] = "06";
		}
		if (aStrings[1].equals("Jul")) {
			aStrings[1] = "07";
		}
		if (aStrings[1].equals("Aug")) {
			aStrings[1] = "08";
		}
		if (aStrings[1].equals("Sep")) {
			aStrings[1] = "09";
		}
		if (aStrings[1].equals("Oct")) {
			aStrings[1] = "10";
		}
		if (aStrings[1].equals("Nov")) {
			aStrings[1] = "11";
		}
		if (aStrings[1].equals("Dec")) {
			aStrings[1] = "12";
		}
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String date = aStrings[5] + "-" + aStrings[1] + "-" + aStrings[2] + " "
				+ aStrings[3];
		Date datetime = null;
		try {
			datetime = df.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datetime;
	}

	public int getCurrentYear() {//返回数字   两位数   现在的年   
		Calendar date = Calendar.getInstance();
		String year = String.valueOf(date.get(Calendar.YEAR));
		String s = year.substring(year.length() - 2, year.length());
		int currentYear = Integer.valueOf(s);
		return currentYear;
	}

	public int getAccountYear(String account) {//返回数字，，两位数   即当前账号对应的年
		String s = account.substring(0, 2);
		int year = Integer.valueOf(s);
		return year;
	}

	public static boolean isNumeric(String str) {//判断是不是输入的全是数字
		if (str.matches("\\d*"))
			return true;
		else
			return false;
	}

	public boolean isOK(String s) {
		if (!isNumeric(s)) {// 如果用户输入的不是数字，返回false
			return false;
		} else if (getCurrentYear() == getAccountYear(s)// 今年与账号的年是同一年，（他是大一新生）
				|| getCurrentYear() == getAccountYear(s) + 1// 今年比账号的年大一年，（他是大一或大二的）
				|| getCurrentYear() == getAccountYear(s) + 2// 今年比账号的年大两年，（他是大三或大二的）
				|| getCurrentYear() == getAccountYear(s) + 3// 今年比账号的年大三年，（他是大三或大四的）
				|| getCurrentYear() == getAccountYear(s) + 4// 今年比账号的年大四年，（他是大四的或已经毕业）
				|| getCurrentYear() > getAccountYear(s)) {////后来加的，毕业之后也可以用，留作纪念嘛  只要今年比学号的年大就可以     跨世纪的话，先不考虑呢
			return true;
		}
		return false;
	}
}
