package course;

import message.Constant;
import userFragments.InformationFragment;
import utils.SQLiteDBUtil;

import com.example.easytablemore.BaseFragment;
import com.example.easytablemore.HttpCallbackListener;
import com.example.easytablemore.HttpUtil;
import com.example.easytablemore.R;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class CourseAddFragment extends BaseFragment{

	private View rootview;
	// ���ڻ�ȡ��ǰ�û��˺ţ���������˺ŵĿγ�
	private SharedPreferences pref;
	private EditText add_txt_name, add_txt_place, add_txt_teacher,
			add_txt_weeks, add_txt_week, add_txt_jie;

	private Button btn_add;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootview = inflater.inflate(R.layout.fragment_course_add, container,
				false);
		// TODO Auto-generated method stub
		return rootview;
	}

	@Override
	protected void initView(View view) {
		// TODO Auto-generated method stub
		super.initView(view);

		add_txt_name = (EditText) rootview.findViewById(R.id.add_txt_name);
		add_txt_place = (EditText) rootview.findViewById(R.id.add_txt_place);
		add_txt_teacher = (EditText) rootview
				.findViewById(R.id.add_txt_teacher);
		add_txt_weeks = (EditText) rootview.findViewById(R.id.add_txt_weeks);
		add_txt_week = (EditText) rootview.findViewById(R.id.add_txt_week);
		add_txt_jie = (EditText) rootview.findViewById(R.id.add_txt_jie);

		btn_add = (Button) rootview.findViewById(R.id.btn_add);
	}

	@Override
	protected void initEvents() {
		// TODO Auto-generated method stub
		super.initEvents();

		String courseId = getArguments().getString("extra_courseid");
		final int getCourseId = Integer.valueOf(courseId);
		final int text_week = getCourseId / 100;
		final int text_jie = getCourseId % 100;
		add_txt_week.setText("��" + text_week + "��");
		add_txt_jie.setText("��" + text_jie + "��");

		pref = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
		final String current_accoutnt = pref.getString("name", "150060408");// ����name�����û���ǰ���˺���

		btn_add.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String add_name = add_txt_name.getText().toString();
				String add_place = add_txt_place.getText().toString();
				String add_teacher = add_txt_teacher.getText().toString();
				String add_weeks = add_txt_weeks.getText().toString();
				int add_week = text_week;
				int add_jie = text_jie;
				int add_id = getCourseId;
				String wb = "http://192.168.137.1:8080/web/user/courseadd.jsp?addid=" + add_id + "&addname="+ add_name + "&addplace=" + add_place+ "&addteacher=" + add_teacher+ "&addweeks=" + add_weeks+ "&addweek=" + add_week+ "&addjie=" + add_jie;
				HttpUtil.sendRequest(wb,
						new HttpCallbackListener() {
							@Override
							public void onFinish(String response) {
								//s = response;
								//show("�������ɹ�");
//								Intent intent = new Intent(Zhuce.this,LoginActivity.class);
//								startActivity(intent);
//								finish();

							}
							@Override
							public void onError(Exception ex) {
								ex.printStackTrace();
								//show("������ʧ��");
							}
						});
				if (!"".equals(add_name)) {// ����û����γ�����û������Ļ����Ǿͱ�����ʾ�û�
					SQLiteDBUtil db = new SQLiteDBUtil(getActivity()
							.getApplicationContext());
					SQLiteDatabase sd = db.getWritableDatabase();
					String sql = "insert into kebiao values(null,?,?,?,?,?,?,?)";
					// sd.execSQL(sql, new
					// Object[]{account,id,add_name,add_teacher,add_place,term,add_weeks});
					sd.execSQL(sql, new Object[] { current_accoutnt,
							getCourseId, add_name, add_teacher, add_place,
							readCurrrentTerm(),// ���ѧ���ǵ�ǰѧ��Ŷ
							add_weeks });
					// ���йر�
					db.close();
					show("��ӳɹ���");
					Intent intent = new Intent("jerry");
					intent.putExtra("change", "courseAdd");
					LocalBroadcastManager.getInstance(getActivity())
							.sendBroadcast(intent);

					// �Զ�����
					getFragmentManager().popBackStack();
				} else {// ���Ϊ�յĻ�������ʾ�û�û�����붫��
					show("�����������ӵĿγ�����Ŷ��");
				}
			}
		});

	}

	private int readCurrrentTerm() {
		pref = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
		String current_accoutnt = pref.getString("name", "150060408");// ����name�����û���ǰ���˺���
		String termKey = current_accoutnt + Constant.CURRENT_TERM; // /��ѧ�ڵļ����мӹ̣�����ÿ���˺�ֻ���Ӧ���Լ���ѧ��
		int currentTerm = pref.getInt(termKey, 1);// ��ͳһ������constant�������
		return currentTerm;
	}

	// ��onviewcreated����� ���Ͻǵĵ��� �����¼�
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		view.findViewById(R.id.image_back).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						getFragmentManager().popBackStack();
					}
				});
	}
}
