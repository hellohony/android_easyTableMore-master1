package utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;
/**
 * 
 * @author ��
 *�������������9��Ϊ��
 */
public class Calendar_day_textView extends TextView {
	
	public boolean isToday=false;
	private Paint paint=new Paint();//��ʼ��paint
	
	public Calendar_day_textView(Context context) {
		super(context);
	}

	public Calendar_day_textView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initControl();
	}

	public Calendar_day_textView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
	}


	private void initControl() {
		paint.setStyle(Paint.Style.STROKE);
		paint.setColor(Color.parseColor("#ff0000"));//���
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		//ԲȦ����
		if (isToday) {
			canvas.translate(getWidth()/2, getHeight()/2);
			canvas.drawCircle(0, 0, getWidth()/2, paint);
		}
	}
}
