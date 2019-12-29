package course;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import noteFragments.NoteShowFragment;

import utils.SQLiteDBUtil;
import utils.Utility;
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
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import bean.Course;
import bean.Note;

import com.example.easytablemore.BaseFragment;
import com.example.easytablemore.HttpCallbackListener;
import com.example.easytablemore.HttpUtil;
import com.example.easytablemore.R;

public class CourseShowFragment extends BaseFragment implements OnClickListener {

	private View rootview;

	private EditText name, teacher, place, weeks, week, jie;
	private Button delete_course;
	private Button save_course;

	// ���ڻ�ȡ��ǰ�û����˺�--��һ���������û�����ؿγ�--�Լ���ʾ��ǰ�Ŀγ��Ǵ��û���
	private SharedPreferences pref;

	// �ʼ�
	private ListView noteList;
	CommonAdapter<Note> myAdapter;
	private static Context context;
	List<Note> notes;// ��Ϊȫ�ֱ�������������

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootview = inflater.inflate(R.layout.fragment_course_show, container,
				false);
		// TODO Auto-generated method stub
		return rootview;
	}

	@Override
	protected void initView(View view) {
		// TODO Auto-generated method stub
		super.initView(view);

		noteList = (ListView) rootview.findViewById(R.id.courseNoteList);

		context = getActivity().getApplicationContext();
		name = (EditText) rootview.findViewById(R.id.id_txt_name);
		teacher = (EditText) rootview.findViewById(R.id.id_txt_teacher);
		place = (EditText) rootview.findViewById(R.id.id_txt_place);
		week = (EditText) rootview.findViewById(R.id.id_txt_week);
		weeks = (EditText) rootview.findViewById(R.id.id_txt_weeks);
		jie = (EditText) rootview.findViewById(R.id.id_txt_jie);

		delete_course = (Button) rootview.findViewById(R.id.delete_course);
		save_course = (Button) rootview.findViewById(R.id.save_course);

		// ����������ֵ��ʼ��
		Course c = (Course) getArguments().getSerializable("course");
		name.setText(c.getName());
		teacher.setText(c.getTeacher());
		place.setText(c.getPlace());
		weeks.setText(c.getWeek());
		week.setText("��" + c.getCourseId() / 100 + "��");
		jie.setText("��" + c.getCourseId() % 100 + "��");
	}

	@Override
	protected void initEvents() {
		// TODO Auto-generated method stub
		super.initEvents();

		Course c = (Course) getArguments().getSerializable("course");

		final String myaccount = c.getAccount();
		final int courseid = c.getCourseId();
		final String getName = c.getName();
		final String getTeacher = c.getTeacher();
		final String getPlace = c.getPlace();
		final String getWeeks = c.getWeek();

		delete_course.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				String wb = "http://192.168.43.166:8080/web/user/coursedelete.jsp?addid=" + courseid;
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
				new AlertDialog.Builder(rootview.getContext())
						// /���������Ի��������û��ڽ���ɾ�����ݲ���
						.setTitle("ȷ�Ͽ�")
						.setMessage("��ȷ��Ҫɾ��������")
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
										String sql = "delete from kebiao where courseId= "
												+ courseid
												+ " AND account= "
												+ myaccount;
										sd.execSQL(sql);
										// ������йر�
										db.close();
										show("��ϲ���h���ɹ���");

										// //���͹㲥����������
										Intent intent = new Intent("jerry");
										intent.putExtra("change",
												"courseDelete");
										LocalBroadcastManager.getInstance(
												getActivity()).sendBroadcast(
												intent);

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
			}
		});

		// ��������޸Ĳ���
		// �����ߵ��ı���Ϣû�н����޸Ĳ�������ô������ʾ��Ϣ����û���޸�
		// ����ı���Ϣ�������޸Ĳ�������ô�����޸���Ϣ��������

		final int courseweek = courseid/ 100;
		final int coursejie = courseid % 100;
		save_course.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				final String editName = name.getText().toString();
				final String editTeacher = teacher.getText().toString();
				final String editPlace = place.getText().toString();
				final String editWeeks = weeks.getText().toString();
				if (getName.equals(editName) && getTeacher.equals(editTeacher)
						&& getPlace.equals(editPlace)
						&& getWeeks.equals(editWeeks)) {
					show("�ף������û���޸���ϢŶ");
				} else {
					String wb = "http://192.168.212.30:8080/web/user/coursechange.jsp?changename=" + editName + "&changeteacher=" + editTeacher+ "&changeplace=" + editPlace+ "&changeweeks=" + editWeeks+ "&courseid=" + courseid+ "&courseweek=" + courseweek+ "&coursejie=" + coursejie;
					HttpUtil.sendRequest(wb,
							new HttpCallbackListener() {
								@Override
								public void onFinish(String response) {
									//showResult("1");
								}
								@Override
								public void onError(Exception ex) {
									ex.printStackTrace();
									//showResult("2");
								}
							});
					new AlertDialog.Builder(rootview.getContext())
							// /���������Ի��������û��ڽ����޸����ݲ���
							.setTitle("ȷ�Ͽ�")
							.setMessage("��ȷ��Ҫ�޸�������")
							.setPositiveButton("ȷ��",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog,
												int which) {
											SQLiteDBUtil db = new SQLiteDBUtil(
													getActivity()
															.getApplicationContext());
											SQLiteDatabase sd = db
													.getWritableDatabase();
											String sql = "update kebiao set name= ?"
													+ " ,teacher= ?"
													+ " ,place= ?"
													+ " ,week= ?"
													+ " where courseId= ?"
													+ " AND account= ?";
											sd.execSQL(sql, new Object[] {
													editName, editTeacher,
													editPlace, editWeeks,
													courseid, myaccount });
											// ������йر�
											db.close();
											show("�޸ĳɹ���Ŷ��");

											// ////���͹㲥��������
											Intent intent = new Intent("jerry");
											intent.putExtra("change",
													"courseShow");
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
										public void onClick(
												DialogInterface dialog,
												int which) {
											// TODO Auto-generated method stub
											show("��ȡ��");
										}
									}).show();
				}

			}
		});

		//��ѯ���ıʼ�
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
											show("��ȡ��");
										}
									}).show();

					break;
				}
				return true;// //����true֮��Ͳ��ᴥ���̰��¼�
			}
		});
		
		
		String text = name.getText().toString();
		notes = querryList(text);
		myAdapter.querryAppend(notes);
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

	// *********************************************

	
	
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

	private List<Note> querryList(String s) {
		List<Note> notes = new ArrayList<Note>();
		Utility util = new Utility();

		SQLiteDBUtil db = new SQLiteDBUtil(getActivity()
				.getApplicationContext());
		SQLiteDatabase sd = db.getReadableDatabase();
		String sql;
		Cursor cursor;

		// ********************************************

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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	private void showResult(final String result){
		getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(getActivity().getApplicationContext(),result,Toast.LENGTH_LONG).show();
			} });
	}

}
