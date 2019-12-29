package userFragments;

import java.util.ArrayList;
import java.util.List;

import message.Constant;
import utils.SQLiteDBUtil;
import adapter.BaseViewHolder;
import adapter.CommonAdapter;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import bean.Course;

import com.example.easytablemore.BaseFragment;
import com.example.easytablemore.R;

public class TermDetailFragment extends BaseFragment implements OnClickListener {

	private View rootview;

	private static Context context;
	private ListView term_list;
	private TextView text_currentTerm;
	private EditText edit_Term;
	private Button btn_addTerm;
	CommonAdapter<Course> myAdapter;
	private SharedPreferences pref;// ���ڱ��浱ǰѧ��

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootview = inflater.inflate(R.layout.fragment_term_detail, container,
				false);
		// TODO Auto-generated method stub
		return rootview;
	}

	@Override
	protected void initView(View view) {
		// TODO Auto-generated method stub
		super.initView(view);

		context = getActivity().getApplicationContext();
		btn_addTerm = (Button) rootview.findViewById(R.id.btn_addTerm);
		edit_Term = (EditText) rootview.findViewById(R.id.edit_Term);
		term_list = (ListView) rootview.findViewById(R.id.term_list);
		text_currentTerm = (TextView) rootview
				.findViewById(R.id.text_currentTerm);
	}

	@Override
	protected void initEvents() {
		// TODO Auto-generated method stub
		super.initEvents();

		btn_addTerm.setOnClickListener(this);// ʱ����onclik��߱�д
		int term = readCurrrentTerm();
		text_currentTerm.setText("��ǰѧ��Ϊ��" + term);

		pref = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
		final String current_accoutnt = pref.getString("name", "150060408");// ����name�����û���ǰ���˺���

		List<Course> courses = new ArrayList<Course>();

		// select distinct term from kebiao;
		// �����ݿ��в�ѯ��һ������Щѧ��
		SQLiteDBUtil db = new SQLiteDBUtil(getActivity()
				.getApplicationContext());
		SQLiteDatabase sd = db.getReadableDatabase();
		// String sql =
		// "select distinct term from kebiao";////////////���д��뱻��һ�����������Ϊ���д���Ļ�������һ���˺ŵ�¼��ѧ�ڻ���֮ǰ��ѧ�ڡ�
		String sql = "select distinct term from kebiao where account="
				+ current_accoutnt;
		Cursor cursor = sd.rawQuery(sql, null);
		while (cursor.moveToNext()) {
			int receive_term = cursor.getInt(0);
			Course c = new Course("", 101, "", "", "", receive_term, "");
			courses.add(c);
		}
		db.close();
		cursor.close();

		term_list.setAdapter(myAdapter = new CommonAdapter<Course>(courses,
				context, R.layout.list_item) {
			@Override
			public void setListener(BaseViewHolder holder) {
				holder.setOnItemClickListener();
				holder.setOnItemLongClickListener();
			}

			@Override
			public void setData(BaseViewHolder holder, Course item) {
				holder.setText(R.id.list_item_tv, item.getTerm() + "");
			}

			@Override
			public void onClickCallback(View v, int position,
					BaseViewHolder viewHolder) {
				switch (v.getId()) {

				default:
					Course c = (Course) term_list.getItemAtPosition(position);
					int term = c.getTerm();
					String s = "�������" + term + "����ǰѧ������Ϊ" + term;
					show(s);
					updateTerm(term);// �������õ�ǰѧ��
					text_currentTerm.setText("��ǰѧ��Ϊ��" + term);// ���½�����ʾѧ��

					// ���͹㲥����������
					Intent intent = new Intent("jerry");
					intent.putExtra("change", "termDetail");
					LocalBroadcastManager.getInstance(getActivity())
							.sendBroadcast(intent);
					break;
				}
			}

			@Override
			public boolean onLonClickCallback(View v, int position,
					BaseViewHolder viewHolder) {
				// TODO Auto-generated method stub

				Course c = (Course) term_list.getItemAtPosition(position);
				final int term = c.getTerm();

				int currentterm = readCurrrentTerm();
				if (term == currentterm) {// �����Ҫɾ����ѧ�ڣ�������ʹ�õ�ѧ�ڣ���ô��ʾ�û�����ɾ����
					show("����ɾ����ѧ��������ʹ�õ�ѧ��Ŷ������ĵ�ǰ��ѧ����ɾ��Ŷ");
				} else {
					new AlertDialog.Builder(rootview.getContext())
							// /���������Ի��������û��ڽ���ɾ�����ݲ���
							.setTitle("ȷ�Ͽ�")
							.setMessage("��ȷ��Ҫɾ����ѧ�����㽫ɾ�����˺������и�ѧ�ڵĿγ�Ŷ����������")
							.setPositiveButton("ȷ��",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											SQLiteDBUtil db = new SQLiteDBUtil(
													getActivity()
															.getApplicationContext());
											SQLiteDatabase sd = db
													.getWritableDatabase();
											String sql = "delete from kebiao where term= "
													+ term
													+ " AND account= "
													+ current_accoutnt;
											sd.execSQL(sql);
											// ������йر�
											db.close();
											show("��ϲ���h��ѧ�ڳɹ���");

											// ����������÷��͹㲥����
											// //���͹㲥����������
											// Intent intent = new
											// Intent("jerry");
											// intent.putExtra("change",
											// "termDelete");
											// LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);

											// �Զ�����
											getFragmentManager().popBackStack();
										}
									})
							.setNegativeButton("ȡ��",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											show("����������ȡ��");
										}
									}).show();
				}

				return true;// �˴������棬����ô�ڳ����¼������󣬾Ͳ��ᴥ���̰��¼���
			}
		});
	}

	private static class MyAdapter extends BaseAdapter {
		private List<Course> courses = new ArrayList<Course>();

		public MyAdapter(List<Course> courses) {
			if (courses != null) {
				this.courses.addAll(courses);
			}
		}

		@Override
		public int getCount() {
			return courses.size();
		}

		@Override
		public Course getItem(int position) {
			if (position > -1 && position < getCount()) {
				return courses.get(position);
			}
			return null;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			ViewHolder holder;
			if (convertView != null) {
				holder = (ViewHolder) convertView.getTag();
				// holder.btn.setOnClickListener(new View.OnClickListener() {
				// @Override
				// public void onClick(View v) {
				// Toast.makeText(context, position + "",
				// Toast.LENGTH_SHORT).show();
				// Log.v(tag, position + "");
				// }
				// });
			} else {
				convertView = LayoutInflater.from(context).inflate(
						R.layout.list_item, parent, false);
				holder = new ViewHolder(convertView);

			}
			Course item = getItem(position);
			if (item != null) {
				holder.tv.setText(item.getTerm() + "");
			}

			return convertView;
		}
	}

	private static class ViewHolder {
		TextView tv;

		public ViewHolder(View convertView) {
			if (convertView != null) {
				convertView.setTag(this);
				tv = (TextView) convertView.findViewById(R.id.list_item_tv);
			}

		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_addTerm:

			pref = getActivity().getSharedPreferences("data",
					Context.MODE_PRIVATE);
			String current_accoutnt = pref.getString("name", "150060408");// ����name�����û���ǰ���˺���

			String currentTerm = edit_Term.getText().toString();
			int term = Integer.valueOf(currentTerm);
			SQLiteDBUtil db = new SQLiteDBUtil(getActivity()
					.getApplicationContext());
			SQLiteDatabase sd = db.getWritableDatabase();
			String sql = "insert into kebiao values(null," + current_accoutnt
					+ ",'','','',''," + term + ",'')";
			// sd.execSQL(sql, new
			// Object[]{account,id,add_name,add_teacher,add_place,term,add_weeks});
			sd.execSQL(sql, new Object[] {});
			// ���йر�
			db.close();
			show("��ӳɹ���");
			edit_Term.setText("");// ���ı�������Ϊ��

			// ���������½�����ʾ
			List<Course> cs = new ArrayList<Course>();
			Course c = new Course(current_accoutnt, 222222, "", "", "", term,
					"");
			cs.add(c);
			myAdapter.append(cs);

			break;

		default:
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

	private void updateTerm(int term) {
		SharedPreferences.Editor editor = getActivity().getSharedPreferences(
				"data", Context.MODE_PRIVATE).edit();
		pref = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
		String current_accoutnt = pref.getString("name", "150060408");// ����name�����û���ǰ���˺���
		String termKey = current_accoutnt + Constant.CURRENT_TERM; // /��ѧ�ڵļ����мӹ̣�����ÿ���˺�ֻ���Ӧ���Լ���ѧ��
		editor.putInt(termKey, term);
		editor.apply();
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
