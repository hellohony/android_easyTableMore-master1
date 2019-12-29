package noteFragments;

import utils.SQLiteDBUtil;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import bean.Note;

import com.example.easytablemore.BaseFragment;
import com.example.easytablemore.R;

public class NoteShowFragment extends BaseFragment {

	private View rootview;
	private EditText note_edit_title, note_edit_course, note_edit_content;
	private Button note_edit_btn;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootview = inflater.inflate(R.layout.fragment_note_show, container,
				false);
		// TODO Auto-generated method stub
		return rootview;
	}

	@Override
	protected void initView(View view) {
		// TODO Auto-generated method stub
		super.initView(view);

		note_edit_title = (EditText) rootview
				.findViewById(R.id.note_edit_title);
		note_edit_course = (EditText) rootview
				.findViewById(R.id.note_edit_course);
		note_edit_content = (EditText) rootview
				.findViewById(R.id.note_edit_content);

		// ����������ֵ��ʾ

		Note note = (Note) getArguments().getSerializable("note");
		note_edit_title.setText(note.getTitle());
		note_edit_course.setText(note.getCourse());
		note_edit_content.setText(note.getContent());

		note_edit_btn = (Button) rootview.findViewById(R.id.note_edit_btn);
	}

	@Override
	protected void initEvents() {
		// TODO Auto-generated method stub
		super.initEvents();

		note_edit_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Note note = (Note) getArguments().getSerializable("note");
				final int id = note.getId();// ��������޸����ݿ�ʱ��
				String privious_title = note.getTitle();
				String privious_course = note.getCourse();
				String privious_content = note.getContent();
				final String current_title = note_edit_title.getText()
						.toString();
				final String current_course = note_edit_course.getText()
						.toString();
				final String current_content = note_edit_content.getText()
						.toString();
				if (privious_title.equals(current_title)
						&& privious_course.equals(current_course)
						&& privious_content.equals(current_content)) {// /��ô��ֵ�����ж��û��Ƿ��޸������ݣ�û���޸����ݾ������û�
					show("�����û���޸�Ŷ��");
				} else {
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
											String sql = "update note set title= ?"
													+ " ,course= ?"
													+ " ,content= ?"
													+ " where id= ?";
											sd.execSQL(sql, new Object[] {
													current_title,
													current_course,
													current_content, id });
											// ������йر�
											db.close();
											show("�޸ĳɹ���Ŷ��");

											// ���͹㲥
											Intent broadCastIntent = new Intent(
													"jack");
											broadCastIntent.putExtra("change",
													"noteEdit");
											LocalBroadcastManager.getInstance(
													getActivity())
													.sendBroadcast(
															broadCastIntent);

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
											show("����������ȡ��");
										}
									}).show();
				}

			}
		});
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
