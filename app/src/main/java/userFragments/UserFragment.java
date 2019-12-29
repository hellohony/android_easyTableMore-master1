package userFragments;

import utils.SQLiteDBUtil;
import com.example.easytablemore.BaseFragment;
import com.example.easytablemore.HttpCallbackListener;
import com.example.easytablemore.HttpUtil;
import com.example.easytablemore.LoginActivity;
import com.example.easytablemore.R;
import com.example.easytablemore.Zhuce;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class UserFragment extends BaseFragment implements OnClickListener {

	private Button btn_exit;
	//private Button btn_genggaixueqi;
	//private Button btn_count_text;
	//private Button btn_want;
	//private Button btn_pifu;
	private Button btn_about_us;
	private Button btn_deleteAll;
	private TextView paomadeng;

	private TextView current_accoutnt;// �ı���������ʾ��ǰ���˺�

	private SharedPreferences pref;// ���ڻ�ȡ��ֵ����

	//long currentCount;
	// ����ǽ�����
	private utils.HorizontalProgressBarWithNumber mProgress;

	private View rootview;
	private Button button;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootview = inflater.inflate(R.layout.fragment_user, container, false);// ��ȡ��ͼ
		return rootview;
	}

	@Override
	protected void initView(View view) {
		super.initView(view);

		btn_exit = (Button) rootview.findViewById(R.id.btn_exit);
		btn_about_us = (Button) rootview.findViewById(R.id.btn_aboutUs);
		//btn_genggaixueqi = (Button) rootview
				//.findViewById(R.id.btn_genggaixueqi);
//		mProgress = (HorizontalProgressBarWithNumber) rootview
//				.findViewById(R.id.myProgress);
		//btn_count_text = (Button) rootview.findViewById(R.id.btn_count_text);
		//btn_pifu = (Button) rootview.findViewById(R.id.btn_pifu);
		btn_deleteAll = (Button) rootview.findViewById(R.id.btn_deleteAll);
		paomadeng = (TextView) rootview.findViewById(R.id.paomadeng);

		current_accoutnt = (TextView) rootview
				.findViewById(R.id.current_account);

		// ��ʼ��ʱ��
		//initTime();
	}

	@Override
	protected void initEvents() {
		super.initEvents();
		//btn_pifu.setOnClickListener(this);
		btn_exit.setOnClickListener(this);
		//btn_count_text.setOnClickListener(this);
		//btn_genggaixueqi.setOnClickListener(this);
		btn_deleteAll.setOnClickListener(this);
		btn_about_us.setOnClickListener(this);

		// ���˺���ʾ
		pref = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
		String name = pref.getString("name", "");
		current_accoutnt.setText("��ǰ���˺ţ�" + name);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btn_aboutUs:
			FragmentTransaction transactionAbout = getActivity()
					.getSupportFragmentManager().beginTransaction();
			AboutUsFragment aboutUsFragment = new AboutUsFragment();
			transactionAbout.add(android.R.id.content, aboutUsFragment,
					"AboutUsFragment");
			transactionAbout.addToBackStack("AboutUsFragment");// ���fragment��Activity�Ļ���ջ��
			transactionAbout.show(aboutUsFragment);
			transactionAbout.commit();
			break;
//		case R.id.btn_pifu:
//			show("ʹ��Ƥ�����ܣ���Ա����ʹ��Ŷ");
//			break;
//		case R.id.btn_count_text:
//			FragmentTransaction transactionTimeDF = getActivity()
//					.getSupportFragmentManager().beginTransaction();
//			TimeDetailFragment timeDetailFragment = new TimeDetailFragment();
//			transactionTimeDF.add(android.R.id.content, timeDetailFragment,
//					"TimeDetailFragment");
//			transactionTimeDF.addToBackStack("TimeDetailFragment");// ���fragment��Activity�Ļ���ջ��
//
//			// ����ֵ
//			Bundle args = new Bundle();
//			args.putInt("currentCount", getCounts());
//			timeDetailFragment.setArguments(args);
//			transactionTimeDF.show(timeDetailFragment);
//			transactionTimeDF.commit();
//
//			break;
//		case R.id.btn_genggaixueqi:
//			FragmentTransaction transactionTermDF = getActivity()
//					.getSupportFragmentManager().beginTransaction();
//			TermDetailFragment termDetailFragment = new TermDetailFragment();
//			transactionTermDF.add(android.R.id.content, termDetailFragment,
//					"TermDetailFragment");
//			transactionTermDF.addToBackStack("TermDetailFragment");// ���fragment��Activity�Ļ���ջ��
//			transactionTermDF.show(termDetailFragment);
//			transactionTermDF.commit();
//
//			break;
		case R.id.btn_deleteAll:
			String wb = "http://192.168.43.166:8080/web/user/deleteall.jsp"; //zafu

			HttpUtil.sendRequest(wb,
					new HttpCallbackListener() {
						@Override
						public void onFinish(String response) {

						}
						@Override
						public void onError(Exception ex) {
							ex.printStackTrace();

						}
					});
			pref = getActivity().getSharedPreferences("data",
					Context.MODE_PRIVATE);
			final String name = pref.getString("name", "");
			new AlertDialog.Builder(rootview.getContext())
					// /���������Ի��������û��ڽ���ɾ�����ݲ���
					.setTitle("ȷ�Ͽ�")
					.setMessage("�����ȷ��Ҫɾ���˺������е���������������Ǻ���ٻ�ȷ����")
					.setPositiveButton("ȷ��",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									SQLiteDBUtil db = new SQLiteDBUtil(
											getActivity()
													.getApplicationContext());
									SQLiteDatabase sd = db
											.getWritableDatabase();
									String sql = "delete from kebiao where account= "
											+ name;
									sd.execSQL(sql);
									// ������йر�
									db.close();
									show("��ϲ���h���ɹ���");

									// //���͹㲥����������
									Intent intent = new Intent("jerry");
									intent.putExtra("change", "deleteAll");
									LocalBroadcastManager.getInstance(
											getActivity())
											.sendBroadcast(intent);

									// �Զ�����
									getFragmentManager().popBackStack();
								}
							})
					.setNegativeButton("ȡ��",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									show("����������ȡ��");
								}
							}).show();

			break;
		case R.id.btn_exit:
			new AlertDialog.Builder(rootview.getContext())
					// /���������Ի��������û��ڽ���ɾ�����ݲ���
					.setTitle("ȷ�Ͽ�")
					.setMessage("��ȷ��Ҫ�˳���¼��")
					.setPositiveButton("ȷ��",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									show("�����˳�");
									changeLoginState(false);// ����¼״̬��Ϊfalse
															// ����ȷ���˳���Ÿı��¼��״̬
									startActivity(new Intent(getActivity(),
											LoginActivity.class));
								}
							})
					.setNegativeButton("ȡ��",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									show("����������ȡ��");
								}
							}).show();
			break;

		default:
			break;
		}
	}

//	/************************************ ���µĶ��ǹ���time����ط��� ***********************************/
//	private void initTime() {
//		// ��ȡ��ǰ�������İٷֱ���
//		int i = getCurrentcount();
//		// ��������ʾ�ڽ�������
//		mProgress.setProgress(i);
//		String s = "��ѧ�����Ѿ��ȹ���" + i + "%,����鿴����";
//		btn_count_text.setText(s);
//	}
//
//	// ���ذٷֱ�
//	private int getCurrentcount() {
//		long count = getCounts();
//		count = count * 100 / 1399;
//		int c = (int) count;
//		return c;
//	}
//
//	// ����������
//	private int getCounts() {
//		// ����Ĭ���Լ���15����Ĭ�Ͽ�ѧʱ����15.9.1���ģ�֮��ᴫ������
//		Utility util = new Utility();
//		pref = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
//		String name = pref.getString("name", "");
//		int year = util.getAccountYear(name);
//
//		// String S15before = "Wed Sep 1 09:22:14 �������α�׼ʱ��+0800 2015";
//		String S15before = "Wed Sep 1 09:22:14 �������α�׼ʱ��+0800 20" + year;// ���д����������һ�д��룬��̬�Ļ�ȡ�û����˺ŵ���
//		Date d1 = new Date();
//		SimpleDateFormat sdf1 = new SimpleDateFormat("yy-MM-dd", Locale.CHINA);
//
//		Date dNow = util.formatDate2(d1.toString());
//		Date d15 = util.formatDate2(S15before);
//
//		long count = (dNow.getTime() - d15.getTime());
//		count = count / 24 / 60 / 60 / 1000;
//		int c = (int) count;
//		return c;
//	}
//
//	/************************************* ���ϵĶ��ǹ���time����ط��� ************************************/

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);

		// fragment�������linearlayout���м���
		view.findViewById(R.id.information).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						FragmentTransaction transaction = getActivity()
								.getSupportFragmentManager().beginTransaction();
						InformationFragment fragment = new InformationFragment();
						transaction.add(android.R.id.content, fragment,
								"InformationFragment");
						transaction.addToBackStack("InformationFragment");// ���fragment��Activity�Ļ���ջ��
						transaction.show(fragment);
						transaction.commit();

					}
				});

		// ��ʼ��ʱ��
		//initTime();
	}

	// ����¼�ˣ��򽫵�¼״̬����Ϊtrue�����˳���¼������Ϊfalse
	public void changeLoginState(Boolean b) {
		SharedPreferences.Editor editor = getActivity().getSharedPreferences(
				"data", Context.MODE_PRIVATE).edit();
		editor.putBoolean("LoginState", b);
		editor.apply();
	}

}
