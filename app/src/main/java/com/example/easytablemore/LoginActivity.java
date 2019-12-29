package com.example.easytablemore;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity implements View.OnClickListener {

	private Button btn,tvForgetPwd;

	private EditText editUserName;
	// ����
	private EditText editPassword;

	// ��ֵ�洢.
	private SharedPreferences pref;

	private  String s;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);

		initView();

		initEvent();

	}

	private void initEvent() {
		// �󶨵����¼�
		tvForgetPwd.setOnClickListener(this);
		btn.setOnClickListener(this);

	}

	// ������ͳһ��ӵ����¼�
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tvForgetPwd:// ע��ĵ����¼�
			Intent intent = new Intent(LoginActivity.this,Zhuce.class);
			startActivity(intent);
			finish();
			break;


		case R.id.btnLogin:// ��¼��ť�ĵ����¼�
				//Utility util = new Utility();
				if ("".equals(editUserName.getText().toString())
						|| "".equals(editPassword.getText().toString())) {
					Toast.makeText(getApplicationContext(), "ѧ�Ż�����Ϊ��",
							Toast.LENGTH_SHORT).show();
				}  else {// �����⣬��ô˵���û����˺�����ȷ����
					String uid=editUserName.getText().toString().trim();
					String pw=editPassword.getText().toString().trim();

					String web = "http://192.168.43.166:8080/web/user/login.jsp?name="+ uid + "&password=" + pw;//zafu
					HttpUtil.sendRequest(web,
							new HttpCallbackListener() {
								@Override
								public void onFinish(String response) {
									s = response;
									checkPassword();
									//showResult(s);

								}
								@Override
								public void onError(Exception ex) {
									ex.printStackTrace();
									showResult("ʧ��");
								}
							});
				}

			break;

		default:
			break;
		}
	}

	protected void checkPassword() {
			if(s.equals("-1"))
			{
				showResult("�˺Ų����ڣ���");
			}
			else if(s.equals("0"))
			{
				showResult("����������󣡣�");
			}
			else
			{
//				checkIsRememberPaw();
				showResult("��¼�ɹ�");
				Intent i = new Intent(LoginActivity.this, MainActivity.class);
				startActivity(i);
			}
			finish();
	}



	private void initView() {
		btn = (Button) findViewById(R.id.btnLogin);
		//rule = (TextView) findViewById(R.id.rule);
		editUserName = (EditText) findViewById(R.id.edit_userName);
		editPassword = (EditText) findViewById(R.id.edit_passWord);
		//rememberPaw = (CheckBox) findViewById(R.id.cb_rememberPsw);
		tvForgetPwd = (Button) findViewById(R.id.tvForgetPwd);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// �����Ƿ��ѵ�¼�Ľ��
	public Boolean LoginState() {
		pref = getSharedPreferences("data", MODE_PRIVATE);
		return pref.getBoolean("LoginState", false);
	}

	// �����Ƿ��ѵ�¼�Ľ��
	public Boolean isRemeber() {
		pref = getSharedPreferences("data", MODE_PRIVATE);
		return pref.getBoolean("isRemeber", false);
	}


	private void showResult(final String result){
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(getApplicationContext(), result,
						Toast.LENGTH_LONG).show();
			}
		});
	}
}
