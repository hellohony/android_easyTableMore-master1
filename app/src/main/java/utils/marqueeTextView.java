package utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
//������ƴ���ѡ��״̬
public class marqueeTextView extends TextView {
	  public marqueeTextView(Context context) {
	        super(context);
	    }

	    public marqueeTextView(Context context, AttributeSet attrs) {
	        super(context, attrs);
	    }

	    public marqueeTextView(Context context, AttributeSet attrs,
	                           int defStyle) {
	        super(context, attrs, defStyle);
	    }
	//һֱ����true����װ����ؼ�һֱ��ȡ�Ž���
	    @Override
	    public boolean isFocused() {
	        return true;
	    }
}
