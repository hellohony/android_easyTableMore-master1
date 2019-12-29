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

	// ���ڵ�ת������

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

	public int getCurrentYear() {//��������   ��λ��   ���ڵ���   
		Calendar date = Calendar.getInstance();
		String year = String.valueOf(date.get(Calendar.YEAR));
		String s = year.substring(year.length() - 2, year.length());
		int currentYear = Integer.valueOf(s);
		return currentYear;
	}

	public int getAccountYear(String account) {//�������֣�����λ��   ����ǰ�˺Ŷ�Ӧ����
		String s = account.substring(0, 2);
		int year = Integer.valueOf(s);
		return year;
	}

	public static boolean isNumeric(String str) {//�ж��ǲ��������ȫ������
		if (str.matches("\\d*"))
			return true;
		else
			return false;
	}

	public boolean isOK(String s) {
		if (!isNumeric(s)) {// ����û�����Ĳ������֣�����false
			return false;
		} else if (getCurrentYear() == getAccountYear(s)// �������˺ŵ�����ͬһ�꣬�����Ǵ�һ������
				|| getCurrentYear() == getAccountYear(s) + 1// ������˺ŵ����һ�꣬�����Ǵ�һ�����ģ�
				|| getCurrentYear() == getAccountYear(s) + 2// ������˺ŵ�������꣬�����Ǵ��������ģ�
				|| getCurrentYear() == getAccountYear(s) + 3// ������˺ŵ�������꣬�����Ǵ�������ĵģ�
				|| getCurrentYear() == getAccountYear(s) + 4// ������˺ŵ�������꣬�����Ǵ��ĵĻ��Ѿ���ҵ��
				|| getCurrentYear() > getAccountYear(s)) {////�����ӵģ���ҵ֮��Ҳ�����ã�����������  ֻҪ�����ѧ�ŵ����Ϳ���     �����͵Ļ����Ȳ�������
			return true;
		}
		return false;
	}
}
