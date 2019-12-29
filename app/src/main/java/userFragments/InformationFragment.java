package userFragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.example.easytablemore.BaseFragment;
import com.example.easytablemore.R;

public class InformationFragment extends BaseFragment {

	private View rootview;
	private TextView infor_text_nicheng, infor_text_account;

	private SharedPreferences pref;// ���ڻ�ȡ��ֵ����

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootview = inflater.inflate(R.layout.fragment_information, container,
				false);
		// TODO Auto-generated method stub
		return rootview;
	}

	@Override
	protected void initView(View view) {
		// TODO Auto-generated method stub
		super.initView(view);

		infor_text_nicheng = (TextView) rootview
				.findViewById(R.id.infor_text_nicheng);
		infor_text_account = (TextView) rootview
				.findViewById(R.id.infor_text_account);

		// ���˺���ʾ
		pref = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
		String text_account = pref.getString("name", "");
		String text_nicheng = pref.getString("nicheng", "����");
		infor_text_account.setText(text_account);
		infor_text_nicheng.setText(text_nicheng);
	}

	@Override
	protected void initEvents() {
		// TODO Auto-generated method stub
		super.initEvents();
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
