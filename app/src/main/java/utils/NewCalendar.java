package utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import com.example.easytablemore.R;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NewCalendar extends LinearLayout {
	
	private ImageView btnPrev;
	private ImageView btnNext;
	private TextView txtDate;
	private GridView grid;
	private Calendar curDate=Calendar.getInstance();
	
/**
 * ��xml�ļ��л��LinearLayout�����
 * @param context
 */
	public NewCalendar(Context context) {
		super(context);
	}

	public NewCalendar(Context context, AttributeSet attrs) {
		super(context, attrs);
		initControl(context);
	}

	public NewCalendar(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);////////////////////////////////////////////////////��Ϊ���д������ҽ�api level��Ϊ��11
		initControl(context);
	}
	
	private void initControl(Context context){//����context
		bindControl(context);//��xml�ļ��еĲ��������ļ��е�������Ӧ
		bindControlEvent();
		renderCalender();
	}

	private void bindControlEvent() {
		btnPrev.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				curDate.add(Calendar.WEEK_OF_YEAR, -1);
				renderCalender();//��Ⱦ
			}
		});
		btnNext.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				curDate.add(Calendar.WEEK_OF_YEAR, 1);
				renderCalender();//��Ⱦ
			}
		});
	}

	private void renderCalender() {
		//�·���ʾ
		SimpleDateFormat sdf=new SimpleDateFormat(" yyyy MM");
		txtDate.setText(sdf.format(curDate.getTime()));
		
		//����չʾ
		ArrayList<Date> cells=new ArrayList();
		Calendar calendar=(Calendar) curDate.clone();
				
		int Days=calendar.get(Calendar.DAY_OF_WEEK)-1;//��Days��ֵ��
		if (Days==0) {
			Days=7;
		}
		calendar.add(Calendar.DAY_OF_WEEK, -Days+1);//��ʾ��Ĭ�ϵĵ�ǰ�����ϼ�-Days+1��
				
		//����cells
		int maxCellCount=1*7;
		while (cells.size()<maxCellCount) {
			cells.add(calendar.getTime());
			calendar.add(Calendar.DAY_OF_WEEK, 1);	//��ʾ��Ĭ�ϵĵ�ǰ�����ϼ�1��
		}
		grid.setAdapter(new CalendarAdapter(getContext(), cells));
	}
	
	private class CalendarAdapter extends ArrayAdapter<Date>{
		LayoutInflater inflater;
		
		public CalendarAdapter(Context context, ArrayList<Date> days) {
			super(context,R.layout.calendar_text_day, days);
			inflater=LayoutInflater.from(context);
		}
		
		 @Override
		public View getView(int position, View convertView, ViewGroup parent) {
			 //��ȡ����
			 Date date=getItem(position);
			 if (convertView==null) {
				convertView=inflater.inflate(R.layout.calendar_text_day, parent,false);
			}
			 int day=date.getDate();
			((TextView) convertView).setText(String.valueOf(day));
			Date now=new Date();
			//�Ǳ������ڵ�����Դ�
			boolean isTheSameMouth=false;
			if (now.getMonth()==date.getMonth()) {
				isTheSameMouth=true;
			}
			if (isTheSameMouth) {
				((TextView) convertView).setTextColor(Color.parseColor("#000000"));
				
			} else {
				((TextView) convertView).setTextColor(Color.parseColor("#666666"));
			}
			
			//�����ر�Դ�

			if (now.getDate()==date.getDate()&&now.getMonth()==date.getMonth()
					&&now.getYear()==date.getYear()) {
				((TextView) convertView).setTextColor(Color.parseColor("#ff0000"));
				((Calendar_day_textView) convertView).isToday=true;
			}
			 return convertView;
		}
		
	}
	
	private void bindControl(Context context) {
		LayoutInflater inflater=LayoutInflater.from(context);
		inflater.inflate(R.layout.calendar_view, this);
		//ʵ����
		btnPrev=(ImageView) findViewById(R.id.btnPrev);
		btnNext=(ImageView) findViewById(R.id.btnNext);
		txtDate=(TextView) findViewById(R.id.txtDate);
		grid=(GridView) findViewById(R.id.grid);
	}
}
