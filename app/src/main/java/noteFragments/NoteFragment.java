package noteFragments;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import userFragments.InformationFragment;
import utils.SQLiteDBUtil;
import utils.Utility;

import bean.Note;

import com.example.easytablemore.BaseFragment;
import com.example.easytablemore.R;

import adapter.BaseViewHolder;
import adapter.CommonAdapter;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class NoteFragment extends BaseFragment implements OnClickListener {

	private View rootview;
	private Button querryBtn;
	private EditText querryText;
	private ListView noteList;
	ImageView noteAdd;
	CommonAdapter<Note> myAdapter;

	List<Note> notes;// ��Ϊȫ�ֱ�������������
	boolean isRegster = false;// ����ȫ�ֱ������ж��Ƿ�ע���˹㲥��

	LocalBroadcastManager broadcastManager;

	private static Context context;
	// ���ڻ�ȡ��ǰ�û����˺�--��һ���������û�����ؿγ�--�Լ���ʾ��ǰ�Ŀγ��Ǵ��û���
	private SharedPreferences pref;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootview = inflater.inflate(R.layout.fragment_note, container, false);

		// ע��㲥 ������ע�ᣬȷ����ִֻ��һ�Ρ���
		if (!isRegster) {// ���ûע�ᣬ��ע�ᣬע����Ͳ��ظ�ע���ˡ�
			registerReceiverNote();
			isRegster = true;// ע����󣬸���Ϊ�Ѿ�ע��
		}
		return rootview;
	}

	@Override
	protected void initView(View view) {
		// TODO Auto-generated method stub
		super.initView(view);
		context = getActivity().getApplicationContext();
		querryBtn = (Button) rootview.findViewById(R.id.querryBtn);
		querryText = (EditText) rootview.findViewById(R.id.querryText);
		noteList = (ListView) rootview.findViewById(R.id.noteList);
		noteAdd = (ImageView) rootview.findViewById(R.id.noteAdd);
	}

	@Override
	protected void initEvents() {
		// TODO Auto-generated method stub
		super.initEvents();
		noteAdd.setOnClickListener(this);
		querryBtn.setOnClickListener(this);

		notes = new ArrayList<Note>();
		Utility util = new Utility();

		SQLiteDBUtil db = new SQLiteDBUtil(getActivity()
				.getApplicationContext());
		SQLiteDatabase sd = db.getReadableDatabase();
		String sql = "select * from note order by id desc";// ��ѯ�Ľ������ʱ������
																// �Ӵ�С(���µ������ϱ�)
		Cursor cursor = sd.rawQuery(sql, null);
		while (cursor.moveToNext()) {
			// account�����ǵ�ǰ���˻��������������ݿ���ѡ����������ݣ�ɸѡ�����˻������ݣ�Ȼ����ʾ�ڽ�����-----

			pref = getActivity().getSharedPreferences("data",
					Context.MODE_PRIVATE);
			String name = pref.getString("name", "150060408");
			int receive_id = cursor.getInt(0);
			String receive_account = cursor.getString(1);

			if (name.equals(receive_account)) {// �ж϶�ȡ�����������ǲ��ǵ�ǰ��¼�û��ģ��ǲ��ǵ�ǰѧ�ڵĿγ̡����ǵĻ�����ʾ
				String receive_title = cursor.getString(2);
				String receive_course = cursor.getString(3);
				String receive_content = cursor.getString(4);
				// �����ݿ��е�ʱ�䣨�ַ�������ʽת��Ϊ��Ӧ�ĸ�ʽ
				String str = "";
				SimpleDateFormat sdf1 = new SimpleDateFormat(
						"yyyy/MM/dd HH:mm", Locale.CHINA);
				Date d = util.formatDate2(cursor.getString(5));
				str = sdf1.format(d);

				Note note = new Note(receive_id, name, receive_title,
						receive_course, receive_content, str);
				notes.add(note);
			}
		}
		// �ر� ���������ûᱨһ�󴮴��󣬵����в��������
		cursor.close();
		db.close();

		noteList.setAdapter(myAdapter = new CommonAdapter<Note>(notes, context,
				R.layout.note_item) {

			@Override
			public void setListener(BaseViewHolder holder) {

				holder.setOnItemClickListener();
				holder.setOnItemLongClickListener();
			}

			@Override
			public void setData(BaseViewHolder holder, Note item) {
				// TODO Auto-generated method stub
				holder.setText(R.id.title, item.getTitle());
				holder.setText(R.id.course, item.getCourse());
				holder.setText(R.id.content, item.getContent());
				holder.setText(R.id.time, item.getTime());
			}

			@Override
			public void onClickCallback(View v, int position,
					BaseViewHolder viewHolder) {
				switch (v.getId()) {

				default:

					Note note = (Note) noteList.getItemAtPosition(position);
					int id = note.getId();

					FragmentTransaction transaction = getActivity()
							.getSupportFragmentManager().beginTransaction();
					NoteShowFragment fragment = new NoteShowFragment();
					transaction.add(android.R.id.content, fragment,
							"InformationFragment");
					transaction.addToBackStack("InformationFragment");// ���fragment��Activity�Ļ���ջ��

					// ����ֵ
					Bundle args = new Bundle();
					args.putSerializable("note", note);
					fragment.setArguments(args);

					transaction.show(fragment);
					transaction.commit();

					break;
				}
			}

			@Override
			public boolean onLonClickCallback(View v, final int position,
					BaseViewHolder viewHolder) {
				switch (v.getId()) {

				default:
					final Note note = (Note) noteList
							.getItemAtPosition(position);
					final int id = note.getId();// /����ɾ���ñʼǵ�ʱ����

					new AlertDialog.Builder(rootview.getContext())
							// /���������Ի��������û��ڽ���ɾ�����ݲ���
							.setTitle("ȷ�Ͽ�")
							.setMessage("��ȷ��Ҫɾ��������")
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
											String sql = "delete from note where id= "
													+ id;
											sd.execSQL(sql);
											// ������йر�
											db.close();

											update(note);
											show("��ϲ���h���ɹ���");
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

					break;
				}
				return true;// //����true֮��Ͳ��ᴥ���̰��¼�
			}
		});
	}

	private static class MyAdapter extends BaseAdapter {

		public List<Note> notes = new ArrayList<Note>();

		public MyAdapter(List<Note> notes) {
			if (notes != null) {
				this.notes.addAll(notes);
			}
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return notes.size();
		}

		@Override
		public Note getItem(int position) {
			if (position > -1 && position < getCount()) {
				return notes.get(position);
			}
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView != null) {
				holder = (ViewHolder) convertView.getTag();
			} else {
				convertView = LayoutInflater.from(context).inflate(
						R.layout.note_item, parent, false);
				holder = new ViewHolder(convertView);
			}
			Note item = getItem(position);
			if (item != null) {
				// holder.tv.setText(item.getName());
				holder.title.setText(item.getTitle());
				holder.course.setText(item.getCourse());
				holder.content.setText(item.getContent());
				holder.time.setText(item.getTime());
			}
			return convertView;
		}
	}

	private static class ViewHolder {
		TextView title, course, content, time;

		public ViewHolder(View convertView) {
			if (convertView != null) {
				convertView.setTag(this);
				title = (TextView) convertView.findViewById(R.id.title);
				course = (TextView) convertView.findViewById(R.id.course);
				content = (TextView) convertView.findViewById(R.id.content);
				time = (TextView) convertView.findViewById(R.id.time);
			}
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.querryBtn:

			String text = querryText.getText().toString();
			notes = querryList(text);
			myAdapter.querryAppend(notes);
			show("��ѯ��" + notes.size() + "������Ŷ");

			break;
		case R.id.noteAdd:

			FragmentTransaction transaction = getActivity()
					.getSupportFragmentManager().beginTransaction();
			NoteAddFragment fragment = new NoteAddFragment();
			transaction.add(android.R.id.content, fragment,
					"InformationFragment");
			transaction.addToBackStack("InformationFragment");// ���fragment��Activity�Ļ���ջ��
			transaction.show(fragment);
			transaction.commit();

			break;
		default:
			break;
		}

	}

	// �ǳ��Ĵ��롷����
	private List<Note> querryList(String s) {
		List<Note> notes = new ArrayList<Note>();
		Utility util = new Utility();

		SQLiteDBUtil db = new SQLiteDBUtil(getActivity()
				.getApplicationContext());
		SQLiteDatabase sd = db.getReadableDatabase();
		String sql;
		Cursor cursor;
		
		// ********************************************
				sql = "select * from note " + "where time like '%" + s + "%'"
						+ "order by id desc";// ��ѯ�Ľ������ʱ������
												// �Ӵ�С(���µ������ϱ�)
				cursor = sd.rawQuery(sql, null);
				while (cursor.moveToNext()) {
					// account�����ǵ�ǰ���˻��������������ݿ���ѡ����������ݣ�ɸѡ�����˻������ݣ�Ȼ����ʾ�ڽ�����-----

					pref = getActivity().getSharedPreferences("data",
							Context.MODE_PRIVATE);
					String name = pref.getString("name", "150060408");
					int receive_id = cursor.getInt(0);
					String receive_account = cursor.getString(1);

					if (name.equals(receive_account)) {// �ж϶�ȡ�����������ǲ��ǵ�ǰ��¼�û��ģ��ǲ��ǵ�ǰѧ�ڵĿγ̡����ǵĻ�����ʾ
						String receive_title = cursor.getString(2);
						String receive_course = cursor.getString(3);
						String receive_content = cursor.getString(4);

						// �����ݿ��е�ʱ�䣨�ַ�������ʽת��Ϊ��Ӧ�ĸ�ʽ
						String str = "";
						SimpleDateFormat sdf1 = new SimpleDateFormat(
								"yyyy/MM/dd HH:mm", Locale.CHINA);
						Date d = util.formatDate2(cursor.getString(5));
						str = sdf1.format(d);

						Note note = new Note(receive_id, name, receive_title,
								receive_course, receive_content, str);
						if (!isIsexist(notes, note)) {
							notes.add(note);
						}
					}
				}
		//***********************************************************************
		sql = "select * from note " + "where title like '%" + s + "%'"
				+ "order by id desc";// ��ѯ�Ľ������ʱ������
										// �Ӵ�С(���µ������ϱ�)
		cursor = sd.rawQuery(sql, null);
		while (cursor.moveToNext()) {
			// account�����ǵ�ǰ���˻��������������ݿ���ѡ����������ݣ�ɸѡ�����˻������ݣ�Ȼ����ʾ�ڽ�����-----

			pref = getActivity().getSharedPreferences("data",
					Context.MODE_PRIVATE);
			String name = pref.getString("name", "150060408");
			int receive_id = cursor.getInt(0);
			String receive_account = cursor.getString(1);

			if (name.equals(receive_account)) {// �ж϶�ȡ�����������ǲ��ǵ�ǰ��¼�û��ģ��ǲ��ǵ�ǰѧ�ڵĿγ̡����ǵĻ�����ʾ
				String receive_title = cursor.getString(2);
				String receive_course = cursor.getString(3);
				String receive_content = cursor.getString(4);

				// �����ݿ��е�ʱ�䣨�ַ�������ʽת��Ϊ��Ӧ�ĸ�ʽ
				String str = "";
				SimpleDateFormat sdf1 = new SimpleDateFormat(
						"yyyy/MM/dd HH:mm", Locale.CHINA);
				Date d = util.formatDate2(cursor.getString(5));
				str = sdf1.format(d);

				Note note = new Note(receive_id, name, receive_title,
						receive_course, receive_content, str);
				if (!isIsexist(notes, note)) {
					notes.add(note);
				}
			}
		}

		// ***************************************************************************
		sql = "select * from note " + "where course like '%" + s + "%'"
				+ "order by id desc";// ��ѯ�Ľ������ʱ������
										// �Ӵ�С(���µ������ϱ�)
		cursor = sd.rawQuery(sql, null);
		while (cursor.moveToNext()) {
			// account�����ǵ�ǰ���˻��������������ݿ���ѡ����������ݣ�ɸѡ�����˻������ݣ�Ȼ����ʾ�ڽ�����-----

			pref = getActivity().getSharedPreferences("data",
					Context.MODE_PRIVATE);
			String name = pref.getString("name", "150060408");
			int receive_id = cursor.getInt(0);
			String receive_account = cursor.getString(1);

			if (name.equals(receive_account)) {// �ж϶�ȡ�����������ǲ��ǵ�ǰ��¼�û��ģ��ǲ��ǵ�ǰѧ�ڵĿγ̡����ǵĻ�����ʾ
				String receive_title = cursor.getString(2);
				String receive_course = cursor.getString(3);
				String receive_content = cursor.getString(4);

				// �����ݿ��е�ʱ�䣨�ַ�������ʽת��Ϊ��Ӧ�ĸ�ʽ
				String str = "";
				SimpleDateFormat sdf1 = new SimpleDateFormat(
						"yyyy/MM/dd HH:mm", Locale.CHINA);
				Date d = util.formatDate2(cursor.getString(5));
				str = sdf1.format(d);

				Note note = new Note(receive_id, name, receive_title,
						receive_course, receive_content, str);
				if (!isIsexist(notes, note)) {
					notes.add(note);
				}
			}
		}
		// ///**********************************************
		sql = "select * from note " + "where content like '%" + s + "%'"
				+ "order by id desc";// ��ѯ�Ľ������ʱ������
										// �Ӵ�С(���µ������ϱ�)
		cursor = sd.rawQuery(sql, null);
		while (cursor.moveToNext()) {
			// account�����ǵ�ǰ���˻��������������ݿ���ѡ����������ݣ�ɸѡ�����˻������ݣ�Ȼ����ʾ�ڽ�����-----

			pref = getActivity().getSharedPreferences("data",
					Context.MODE_PRIVATE);
			String name = pref.getString("name", "150060408");
			int receive_id = cursor.getInt(0);
			String receive_account = cursor.getString(1);

			if (name.equals(receive_account)) {// �ж϶�ȡ�����������ǲ��ǵ�ǰ��¼�û��ģ��ǲ��ǵ�ǰѧ�ڵĿγ̡����ǵĻ�����ʾ
				String receive_title = cursor.getString(2);
				String receive_course = cursor.getString(3);
				String receive_content = cursor.getString(4);

				// �����ݿ��е�ʱ�䣨�ַ�������ʽת��Ϊ��Ӧ�ĸ�ʽ
				String str = "";
				SimpleDateFormat sdf1 = new SimpleDateFormat(
						"yyyy/MM/dd HH:mm", Locale.CHINA);
				Date d = util.formatDate2(cursor.getString(5));
				str = sdf1.format(d);

				Note note = new Note(receive_id, name, receive_title,
						receive_course, receive_content, str);
				if (!isIsexist(notes, note)) {
					notes.add(note);
				}
			}
		}
		

		// �ر� ���������ûᱨһ�󴮴��󣬵����в��������
		cursor.close();
		db.close();

		return notes;
	}

	// �ǳ��Ĵ��롶����

	private boolean isIsexist(List<Note> notes, Note note) {// �жϼ������Ƿ��Ѿ����������Ԫ��
		for (Note n : notes) {
			if (note.getId() == n.getId()) {
				return true;
			}
		}
		return false;
	}

	// ///**************************һ����ͨ���㲥��ʵ����ӱʼ� �Զ����½���******************����
	// ��onViewCreateע��Ĺ㲥

	/**
	 * ע��㲥������
	 */
	private void registerReceiverNote() {
		broadcastManager = LocalBroadcastManager.getInstance(getActivity());
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction("jack");
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
			if ("noteAdd".equals(change) || "noteEdit".equals(change)) {// ��ӱʼǴ������Ĺ㲥
				// ��ط�ֻ�������߳���ˢ��UI,���߳�����Ч�������Handler��ʵ��
				new Handler().post(new Runnable() {
					public void run() {
						// ��������д����Ҫˢ�µĵط�
						// ���磺testView.setText("��ϲ��ɹ���");
						// show("������������Ҫ�����������������");
						initEvents();
					}
				});
			}
		}
	};
	// ///**************************һ����ͨ���㲥��ʵ����ӱʼ� �Զ����½���******************����
}
