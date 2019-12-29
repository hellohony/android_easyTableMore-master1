package course;

import java.util.HashMap;
import java.util.Map;


import message.Constant;

import utils.SQLiteDBUtil;

import bean.Course;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.easytablemore.BaseFragment;
import com.example.easytablemore.R;

public class CourseFragment extends BaseFragment implements OnClickListener {

	private View rootview;
	// ���ڻ�ȡ��ǰ�û����˺�--��һ���������û�����ؿγ�--�Լ���ʾ��ǰ�Ŀγ��Ǵ��û���
	private SharedPreferences pref;
	

	
	LocalBroadcastManager broadcastManager;
	boolean isRegster = false;// ����ȫ�ֱ������ж��Ƿ�ע���˹㲥��

	private Button btn_1_1, btn_2_1, btn_3_1, btn_4_1, btn_5_1, btn_6_1,
			btn_7_1;
	private Button btn_1_2, btn_2_2, btn_3_2, btn_4_2, btn_5_2, btn_6_2,
			btn_7_2;
	private Button btn_1_3, btn_2_3, btn_3_3, btn_4_3, btn_5_3, btn_6_3,
			btn_7_3;
	private Button btn_1_4, btn_2_4, btn_3_4, btn_4_4, btn_5_4, btn_6_4,
			btn_7_4;
	private Button btn_1_5, btn_2_5, btn_3_5, btn_4_5, btn_5_5, btn_6_5,
			btn_7_5;
	private Button btn_1_6, btn_2_6, btn_3_6, btn_4_6, btn_5_6, btn_6_6,
			btn_7_6;
	private Button btn_1_7, btn_2_7, btn_3_7, btn_4_7, btn_5_7, btn_6_7,
			btn_7_7;
	private Button btn_1_8, btn_2_8, btn_3_8, btn_4_8, btn_5_8, btn_6_8,
			btn_7_8;
	private Button btn_1_9, btn_2_9, btn_3_9, btn_4_9, btn_5_9, btn_6_9,
			btn_7_9;
	private Button btn_1_10, btn_2_10, btn_3_10, btn_4_10, btn_5_10, btn_6_10,
			btn_7_10;
	private Button btn_1_11, btn_2_11, btn_3_11, btn_4_11, btn_5_11, btn_6_11,
			btn_7_11;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootview = inflater.inflate(R.layout.fragment_course, container, false);
		// TODO Auto-generated method stub

		// ע��㲥 ������ע�ᣬȷ����ִֻ��һ�Ρ���
		if (!isRegster) {// ���ûע�ᣬ��ע�ᣬע����Ͳ��ظ�ע���ˡ�
			registerReceiver();
			isRegster = true;// ע����󣬸���Ϊ�Ѿ�ע��
		}
		return rootview;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);

		// ��ʼ����������
		receiveData();
		// ��ʼ����ť�������ť��û�пγ̵Ļ�����ô���ʱ����������ӿγ̵Ľ���
		initAllButton();

	}

	@Override
	protected void initView(View view) {
		super.initView(view);
		


		// ��1�а�ť id_btn_��_��
		btn_1_1 = (Button) rootview.findViewById(R.id.id_btn_1_1);
		btn_2_1 = (Button) rootview.findViewById(R.id.id_btn_2_1);
		btn_3_1 = (Button) rootview.findViewById(R.id.id_btn_3_1);
		btn_4_1 = (Button) rootview.findViewById(R.id.id_btn_4_1);
		btn_5_1 = (Button) rootview.findViewById(R.id.id_btn_5_1);
		btn_6_1 = (Button) rootview.findViewById(R.id.id_btn_6_1);
		btn_7_1 = (Button) rootview.findViewById(R.id.id_btn_7_1);
		// ��2�а�ť id_btn_��_��
		btn_1_2 = (Button) rootview.findViewById(R.id.id_btn_1_2);
		btn_2_2 = (Button) rootview.findViewById(R.id.id_btn_2_2);
		btn_3_2 = (Button) rootview.findViewById(R.id.id_btn_3_2);
		btn_4_2 = (Button) rootview.findViewById(R.id.id_btn_4_2);
		btn_5_2 = (Button) rootview.findViewById(R.id.id_btn_5_2);
		btn_6_2 = (Button) rootview.findViewById(R.id.id_btn_6_2);
		btn_7_2 = (Button) rootview.findViewById(R.id.id_btn_7_2);
		// ��3�а�ť id_btn_��_��
		btn_1_3 = (Button) rootview.findViewById(R.id.id_btn_1_3);
		btn_2_3 = (Button) rootview.findViewById(R.id.id_btn_2_3);
		btn_3_3 = (Button) rootview.findViewById(R.id.id_btn_3_3);
		btn_4_3 = (Button) rootview.findViewById(R.id.id_btn_4_3);
		btn_5_3 = (Button) rootview.findViewById(R.id.id_btn_5_3);
		btn_6_3 = (Button) rootview.findViewById(R.id.id_btn_6_3);
		btn_7_3 = (Button) rootview.findViewById(R.id.id_btn_7_3);
		// ��4�а�ť id_btn_��_��
		btn_1_4 = (Button) rootview.findViewById(R.id.id_btn_1_4);
		btn_2_4 = (Button) rootview.findViewById(R.id.id_btn_2_4);
		btn_3_4 = (Button) rootview.findViewById(R.id.id_btn_3_4);
		btn_4_4 = (Button) rootview.findViewById(R.id.id_btn_4_4);
		btn_5_4 = (Button) rootview.findViewById(R.id.id_btn_5_4);
		btn_6_4 = (Button) rootview.findViewById(R.id.id_btn_6_4);
		btn_7_4 = (Button) rootview.findViewById(R.id.id_btn_7_4);
		// ��5�а�ť id_btn_��_��
		btn_1_5 = (Button) rootview.findViewById(R.id.id_btn_1_5);
		btn_2_5 = (Button) rootview.findViewById(R.id.id_btn_2_5);
		btn_3_5 = (Button) rootview.findViewById(R.id.id_btn_3_5);
		btn_4_5 = (Button) rootview.findViewById(R.id.id_btn_4_5);
		btn_5_5 = (Button) rootview.findViewById(R.id.id_btn_5_5);
		btn_6_5 = (Button) rootview.findViewById(R.id.id_btn_6_5);
		btn_7_5 = (Button) rootview.findViewById(R.id.id_btn_7_5);
		// ��6�а�ť id_btn_��_��
		btn_1_6 = (Button) rootview.findViewById(R.id.id_btn_1_6);
		btn_2_6 = (Button) rootview.findViewById(R.id.id_btn_2_6);
		btn_3_6 = (Button) rootview.findViewById(R.id.id_btn_3_6);
		btn_4_6 = (Button) rootview.findViewById(R.id.id_btn_4_6);
		btn_5_6 = (Button) rootview.findViewById(R.id.id_btn_5_6);
		btn_6_6 = (Button) rootview.findViewById(R.id.id_btn_6_6);
		btn_7_6 = (Button) rootview.findViewById(R.id.id_btn_7_6);
		// ��7�а�ť id_btn_��_��
		btn_1_7 = (Button) rootview.findViewById(R.id.id_btn_1_7);
		btn_2_7 = (Button) rootview.findViewById(R.id.id_btn_2_7);
		btn_3_7 = (Button) rootview.findViewById(R.id.id_btn_3_7);
		btn_4_7 = (Button) rootview.findViewById(R.id.id_btn_4_7);
		btn_5_7 = (Button) rootview.findViewById(R.id.id_btn_5_7);
		btn_6_7 = (Button) rootview.findViewById(R.id.id_btn_6_7);
		btn_7_7 = (Button) rootview.findViewById(R.id.id_btn_7_7);
		// ��8�а�ť id_btn_��_��
		btn_1_8 = (Button) rootview.findViewById(R.id.id_btn_1_8);
		btn_2_8 = (Button) rootview.findViewById(R.id.id_btn_2_8);
		btn_3_8 = (Button) rootview.findViewById(R.id.id_btn_3_8);
		btn_4_8 = (Button) rootview.findViewById(R.id.id_btn_4_8);
		btn_5_8 = (Button) rootview.findViewById(R.id.id_btn_5_8);
		btn_6_8 = (Button) rootview.findViewById(R.id.id_btn_6_8);
		btn_7_8 = (Button) rootview.findViewById(R.id.id_btn_7_8);
		// ��9�а�ť id_btn_��_��
		btn_1_9 = (Button) rootview.findViewById(R.id.id_btn_1_9);
		btn_2_9 = (Button) rootview.findViewById(R.id.id_btn_2_9);
		btn_3_9 = (Button) rootview.findViewById(R.id.id_btn_3_9);
		btn_4_9 = (Button) rootview.findViewById(R.id.id_btn_4_9);
		btn_5_9 = (Button) rootview.findViewById(R.id.id_btn_5_9);
		btn_6_9 = (Button) rootview.findViewById(R.id.id_btn_6_9);
		btn_7_9 = (Button) rootview.findViewById(R.id.id_btn_7_9);
		// ��10�а�ť id_btn_��_��
		btn_1_10 = (Button) rootview.findViewById(R.id.id_btn_1_10);
		btn_2_10 = (Button) rootview.findViewById(R.id.id_btn_2_10);
		btn_3_10 = (Button) rootview.findViewById(R.id.id_btn_3_10);
		btn_4_10 = (Button) rootview.findViewById(R.id.id_btn_4_10);
		btn_5_10 = (Button) rootview.findViewById(R.id.id_btn_5_10);
		btn_6_10 = (Button) rootview.findViewById(R.id.id_btn_6_10);
		btn_7_10 = (Button) rootview.findViewById(R.id.id_btn_7_10);
		// ��11�а�ť id_btn_��_��
		btn_1_11 = (Button) rootview.findViewById(R.id.id_btn_1_11);
		btn_2_11 = (Button) rootview.findViewById(R.id.id_btn_2_11);
		btn_3_11 = (Button) rootview.findViewById(R.id.id_btn_3_11);
		btn_4_11 = (Button) rootview.findViewById(R.id.id_btn_4_11);
		btn_5_11 = (Button) rootview.findViewById(R.id.id_btn_5_11);
		btn_6_11 = (Button) rootview.findViewById(R.id.id_btn_6_11);
		btn_7_11 = (Button) rootview.findViewById(R.id.id_btn_7_11);
	}

	@Override
	protected void initEvents() {
		// TODO Auto-generated method stub
		super.initEvents();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	protected void receiveData() {

		SQLiteDBUtil db = new SQLiteDBUtil(getActivity()
				.getApplicationContext());
		SQLiteDatabase sd = db.getReadableDatabase();
		String sql = "select * from kebiao";
		Cursor cursor = sd.rawQuery(sql, null);
		while (cursor.moveToNext()) {
			// account�����ǵ�ǰ���˻��������������ݿ���ѡ����������ݣ�ɸѡ�����˻������ݣ�Ȼ����ʾ�ڽ�����-----
			// ---------------------ѧ��Ҳ����������ûʱ��д��

			pref = getActivity().getSharedPreferences("data",
					Context.MODE_PRIVATE);
			String name = pref.getString("name", "150060408");
			String receive_account = cursor.getString(1);
			int term = readCurrrentTerm();
			int receive_term = cursor.getInt(6);

			if (name.equals(receive_account) && term == receive_term) {// �ж϶�ȡ�����������ǲ��ǵ�ǰ��¼�û��ģ��ǲ��ǵ�ǰѧ�ڵĿγ̡����ǵĻ�����ʾ
				int receive_courseId = cursor.getInt(2);
				String receive_name = cursor.getString(3);
				String receive_teacher = cursor.getString(4);
				String receive_place = cursor.getString(5);
				String receive_week = cursor.getString(7);

				Course receive_course = new Course(receive_account,
						receive_courseId, receive_name, receive_teacher,
						receive_place, receive_term, receive_week);
				showCourse(receive_course);
			}
		}
		// �ر� ���������ûᱨһ�󴮴��󣬵����в��������
		cursor.close();
		db.close();

	}

	protected void initAllButton() {
		Map<String, Button> map = new HashMap<String, Button>();
		// �����е�button���б�ţ����ַ�������ʽ��
		String[] strings = { "101", "102", "103", "104", "105", "106", "107",
				"108", "109", "110", "111", "201", "202", "203", "204", "205",
				"206", "207", "208", "209", "210", "211", "301", "302", "303",
				"304", "305", "306", "307", "308", "309", "310", "311", "401",
				"402", "403", "404", "405", "406", "407", "408", "409", "410",
				"411", "501", "502", "503", "504", "505", "506", "507", "508",
				"509", "510", "511", "601", "602", "603", "604", "605", "606",
				"607", "608", "609", "610", "611", "701", "702", "703", "704",
				"705", "706", "707", "708", "709", "710", "711" };
		Button[] buttons = { btn_1_1, btn_1_2, btn_1_3, btn_1_4, btn_1_5,
				btn_1_6, btn_1_7, btn_1_8, btn_1_9, btn_1_10, btn_1_11,
				btn_2_1, btn_2_2, btn_2_3, btn_2_4, btn_2_5, btn_2_6, btn_2_7,
				btn_2_8, btn_2_9, btn_2_10, btn_2_11, btn_3_1, btn_3_2,
				btn_3_3, btn_3_4, btn_3_5, btn_3_6, btn_3_7, btn_3_8, btn_3_9,
				btn_3_10, btn_3_11, btn_4_1, btn_4_2, btn_4_3, btn_4_4,
				btn_4_5, btn_4_6, btn_4_7, btn_4_8, btn_4_9, btn_4_10,
				btn_4_11, btn_5_1, btn_5_2, btn_5_3, btn_5_4, btn_5_5, btn_5_6,
				btn_5_7, btn_5_8, btn_5_9, btn_5_10, btn_5_11, btn_6_1,
				btn_6_2, btn_6_3, btn_6_4, btn_6_5, btn_6_6, btn_6_7, btn_6_8,
				btn_6_9, btn_6_10, btn_6_11, btn_7_1, btn_7_2, btn_7_3,
				btn_7_4, btn_7_5, btn_7_6, btn_7_7, btn_7_8, btn_7_9, btn_7_10,
				btn_7_11 };
		// �����е�button���Ŵ洢��map��
		for (int i = 0; i < strings.length; i++) {
			map.put(strings[i], buttons[i]);
		}

		// ͨ���������еļ������������е�button�� �����ť���ı�Ϊ�յĻ�����ת����ӿγ̵Ľ���
		for (final String key : map.keySet()) {
			Button b = map.get(key);
			if ("".equals(b.getText())) {
				b.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {

						FragmentTransaction transaction = getActivity()
								.getSupportFragmentManager().beginTransaction();
						CourseAddFragment fragment = new CourseAddFragment();
						transaction.add(android.R.id.content, fragment, "aaa");
						transaction.addToBackStack("aaa");// ���fragment��Activity�Ļ���ջ��
						transaction.show(fragment);

						// ����ֵ
						Bundle args = new Bundle();
						args.putString("extra_courseid", key);
						fragment.setArguments(args);

						transaction.commit();

					}
				});
			} else {// ���ı����ǿյģ���ô˵������пΡ�//��ʱ����֪����ôд������
					// TODO
			}
		}

	}

	private void initAction(final Button btn, final Course c) {
		String content = c.getName();

		btn.setText(content);

		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentTransaction transaction = getActivity()
						.getSupportFragmentManager().beginTransaction();
				CourseShowFragment fragment = new CourseShowFragment();
				transaction.add(android.R.id.content, fragment,
						"CourseShowFragment");
				transaction.addToBackStack("CourseShowFragment");// ���fragment��Activity�Ļ���ջ��

				// ����ֵ
				Bundle args = new Bundle();
				args.putSerializable("course", c);
				fragment.setArguments(args);
				transaction.show(fragment);

				transaction.commit();
			}
		});
	}

	// ��ʾ�γ�
	private void showCourse(final Course c) {
		if ("".equals(c.getName())) {
			return;
		}
		int day = c.getCourseId() / 100;
		int part = c.getCourseId() % 100;
		switch (day) {// �ܼ�
		case 1:
			switch (part) {// �ڼ��ڿ�
			case 1:
				initAction(btn_1_1, c);
				break;
			case 2:
				initAction(btn_1_2, c);
				break;
			case 3:
				initAction(btn_1_3, c);
				break;
			case 4:
				initAction(btn_1_4, c);
				break;
			case 5:
				initAction(btn_1_5, c);
				break;
			case 6:
				initAction(btn_1_6, c);
				break;
			case 7:
				initAction(btn_1_7, c);
				break;
			case 8:
				initAction(btn_1_8, c);
				break;
			case 9:
				initAction(btn_1_9, c);
				break;
			case 10:
				initAction(btn_1_10, c);
				break;
			case 11:
				initAction(btn_1_11, c);
				break;

			default:
				break;
			}
			break;

		case 2:
			switch (part) {// �ڼ��ڿ�
			case 1:
				initAction(btn_2_1, c);
				break;
			case 2:
				initAction(btn_2_2, c);
				break;
			case 3:
				initAction(btn_2_3, c);
				break;
			case 4:
				initAction(btn_2_4, c);
				break;
			case 5:
				initAction(btn_2_5, c);
				break;
			case 6:
				initAction(btn_2_6, c);
				break;
			case 7:
				initAction(btn_2_7, c);
				break;
			case 8:
				initAction(btn_2_8, c);
				break;
			case 9:
				initAction(btn_2_9, c);
				break;
			case 10:
				initAction(btn_2_10, c);
				break;
			case 11:
				initAction(btn_2_11, c);
				break;

			default:
				break;
			}
			break;

		case 3:
			switch (part) {// �ڼ��ڿ�
			case 1:
				initAction(btn_3_1, c);
				break;
			case 2:
				initAction(btn_3_2, c);
				break;
			case 3:
				initAction(btn_3_3, c);
				break;
			case 4:
				initAction(btn_3_4, c);
				break;
			case 5:
				initAction(btn_3_5, c);
				break;
			case 6:
				initAction(btn_3_6, c);
				break;
			case 7:
				initAction(btn_3_7, c);
				break;
			case 8:
				initAction(btn_3_8, c);
				break;
			case 9:
				initAction(btn_3_9, c);
				break;
			case 10:
				initAction(btn_3_10, c);
				break;
			case 11:
				initAction(btn_3_11, c);
				break;

			default:
				break;
			}
			break;

		case 4:
			switch (part) {// �ڼ��ڿ�
			case 1:
				initAction(btn_4_1, c);
				break;
			case 2:
				initAction(btn_4_2, c);
				break;
			case 3:
				initAction(btn_4_3, c);
				break;
			case 4:
				initAction(btn_4_4, c);
				break;
			case 5:
				initAction(btn_4_5, c);
				break;
			case 6:
				initAction(btn_4_6, c);
				break;
			case 7:
				initAction(btn_4_7, c);
				break;
			case 8:
				initAction(btn_4_8, c);
				break;
			case 9:
				initAction(btn_4_9, c);
				break;
			case 10:
				initAction(btn_4_10, c);
				break;
			case 11:
				initAction(btn_4_11, c);
				break;

			default:
				break;
			}
			break;

		case 5:
			switch (part) {// �ڼ��ڿ�
			case 1:
				initAction(btn_5_1, c);
				break;
			case 2:
				initAction(btn_5_2, c);
				break;
			case 3:
				initAction(btn_5_3, c);
				break;
			case 4:
				initAction(btn_5_4, c);
				break;
			case 5:
				initAction(btn_5_5, c);
				break;
			case 6:
				initAction(btn_5_6, c);
				break;
			case 7:
				initAction(btn_5_7, c);
				break;
			case 8:
				initAction(btn_5_8, c);
				break;
			case 9:
				initAction(btn_5_9, c);
				break;
			case 10:
				initAction(btn_5_10, c);
				break;
			case 11:
				initAction(btn_5_11, c);
				break;

			default:
				break;
			}
			break;

		case 6:
			switch (part) {// �ڼ��ڿ�
			case 1:
				initAction(btn_6_1, c);
				break;
			case 2:
				initAction(btn_6_2, c);
				break;
			case 3:
				initAction(btn_6_3, c);
				break;
			case 4:
				initAction(btn_6_4, c);
				break;
			case 5:
				initAction(btn_6_5, c);
				break;
			case 6:
				initAction(btn_6_6, c);
				break;
			case 7:
				initAction(btn_6_7, c);
				break;
			case 8:
				initAction(btn_6_8, c);
				break;
			case 9:
				initAction(btn_6_9, c);
				break;
			case 10:
				initAction(btn_6_10, c);
				break;
			case 11:
				initAction(btn_6_11, c);
				break;

			default:
				break;
			}
			break;

		case 7:
			switch (part) {// �ڼ��ڿ�
			case 1:
				initAction(btn_7_1, c);
				break;
			case 2:
				initAction(btn_7_2, c);
				break;
			case 3:
				initAction(btn_7_3, c);
				break;
			case 4:
				initAction(btn_7_4, c);
				break;
			case 5:
				initAction(btn_7_5, c);
				break;
			case 6:
				initAction(btn_7_6, c);
				break;
			case 7:
				initAction(btn_7_7, c);
				break;
			case 8:
				initAction(btn_7_8, c);
				break;
			case 9:
				initAction(btn_7_9, c);
				break;
			case 10:
				initAction(btn_7_10, c);
				break;
			case 11:
				initAction(btn_7_11, c);
				break;

			default:
				break;
			}
			break;
		}
	}

	private int readCurrrentTerm() {
		pref = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
		String current_accoutnt = pref.getString("name", "150060408");// ����name�����û���ǰ���˺���
		String termKey = current_accoutnt + Constant.CURRENT_TERM; // /��ѧ�ڵļ����мӹ̣�����ÿ���˺�ֻ���Ӧ���Լ���ѧ��
		int currentTerm = pref.getInt(termKey, 1);// ��ͳһ������constant�������
		return currentTerm;
	}

	/**
	 * ע��㲥������
	 */
	private void registerReceiver() {
		broadcastManager = LocalBroadcastManager.getInstance(getActivity());
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction("jerry");
		broadcastManager.registerReceiver(mAdDownLoadReceiver, intentFilter);
	}

	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		super.onDetach();
		broadcastManager.unregisterReceiver(mAdDownLoadReceiver);
	}

	private BroadcastReceiver mAdDownLoadReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String change = intent.getStringExtra("change");
			// �ֱ�����ô���ǩ������Ϊ���ֻ��һ����ǩ��ÿ�ε������һ������ô�����潫����ºö�Ŷ�Σ��������м���������һ���ı�ǩ���͸��¼��Σ���
			if ("courseDelete".equals(change) // ɾ���γ̴������Ĺ㲥
					|| "courseAdd".equals(change)// ��ӿγ̴������Ĺ㲥
					|| "courseShow".equals(change)// �޸Ŀγ̴������Ĺ㲥
					|| "deleteAll".equals(change)// ɾ���˺������пγ̴������Ĺ㲥
					// || "termDelete".equals(change)//
					// ɾ��ѡ�е�ѧ�ڴ������Ĺ㲥*****************���Ŀǰ����Ҫ�㲥
					|| "termDetail".equals(change)) {// �޸ĵ�ǰѧ�ڴ������Ĺ㲥
				// ��ط�ֻ�������߳���ˢ��UI,���߳�����Ч�������Handler��ʵ��
				new Handler().post(new Runnable() {
					public void run() {
						// ��������д����Ҫˢ�µĵط�
						// ���磺testView.setText("��ϲ��ɹ���");
						// show("������������Ҫ�����������������");
						clearAll();

						// ��ʼ����������
						receiveData();
						// ����������Ҫ��ʼ������button�ģ��������û�����������������ɾ���γ̺��ٵ��ɾ���γ̵�λ�ã������ÿγ���ϸ��Ϣ���棡����
						initAllButton();
					}
				});
			}
		}
	};

	// ���й㲥����Ҫ�Խ������ˢ�£�ˢ�µĵ�һ���������������
	public void clearAll() {
		Map<String, Button> map = new HashMap<String, Button>();
		// �����е�button���б�ţ����ַ�������ʽ��
		String[] strings = { "101", "102", "103", "104", "105", "106", "107",
				"108", "109", "110", "111", "201", "202", "203", "204", "205",
				"206", "207", "208", "209", "210", "211", "301", "302", "303",
				"304", "305", "306", "307", "308", "309", "310", "311", "401",
				"402", "403", "404", "405", "406", "407", "408", "409", "410",
				"411", "501", "502", "503", "504", "505", "506", "507", "508",
				"509", "510", "511", "601", "602", "603", "604", "605", "606",
				"607", "608", "609", "610", "611", "701", "702", "703", "704",
				"705", "706", "707", "708", "709", "710", "711" };
		Button[] buttons = { btn_1_1, btn_1_2, btn_1_3, btn_1_4, btn_1_5,
				btn_1_6, btn_1_7, btn_1_8, btn_1_9, btn_1_10, btn_1_11,
				btn_2_1, btn_2_2, btn_2_3, btn_2_4, btn_2_5, btn_2_6, btn_2_7,
				btn_2_8, btn_2_9, btn_2_10, btn_2_11, btn_3_1, btn_3_2,
				btn_3_3, btn_3_4, btn_3_5, btn_3_6, btn_3_7, btn_3_8, btn_3_9,
				btn_3_10, btn_3_11, btn_4_1, btn_4_2, btn_4_3, btn_4_4,
				btn_4_5, btn_4_6, btn_4_7, btn_4_8, btn_4_9, btn_4_10,
				btn_4_11, btn_5_1, btn_5_2, btn_5_3, btn_5_4, btn_5_5, btn_5_6,
				btn_5_7, btn_5_8, btn_5_9, btn_5_10, btn_5_11, btn_6_1,
				btn_6_2, btn_6_3, btn_6_4, btn_6_5, btn_6_6, btn_6_7, btn_6_8,
				btn_6_9, btn_6_10, btn_6_11, btn_7_1, btn_7_2, btn_7_3,
				btn_7_4, btn_7_5, btn_7_6, btn_7_7, btn_7_8, btn_7_9, btn_7_10,
				btn_7_11 };
		// �����е�button���Ŵ洢��map��
		for (int i = 0; i < strings.length; i++) {
			map.put(strings[i], buttons[i]);
		}
		// ͨ���������еļ������������е�button�� �����������
		for (final String key : map.keySet()) {
			Button b = map.get(key);
			b.setText("");
		}
	}
	
	

}
