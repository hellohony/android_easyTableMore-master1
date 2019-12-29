package com.example.easytablemore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Zhuce extends LoginActivity implements View.OnClickListener{
    private EditText zhuceUserName,zhucePassword;
    private  String s;
    private Button zhuceback,zc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);
        initView();
        initEvent();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.back:
            Intent intent = new Intent(Zhuce.this,LoginActivity.class);
            startActivity(intent);
            finish();
            break;
        case R.id.zhuce:
			String zhuceid=zhuceUserName.getText().toString().trim();
			String zhucepw=zhucePassword.getText().toString().trim();

            String wb = "http://192.168.43.166:8080/web/user/register.jsp?name="+ zhuceid + "&password=" + zhucepw; //zafu

			HttpUtil.sendRequest(wb,
					new HttpCallbackListener() {

						@Override
						public void onFinish(String response) {
							s = response;
							showResult("注册成功");
                            Intent intent = new Intent(Zhuce.this,LoginActivity.class);
                            startActivity(intent);
                            finish();
							//showResult(s);
						}
						@Override
						public void onError(Exception ex) {
							ex.printStackTrace();
							showResult("失败");
						}
					});


            default:
                break;
        }
    }
    private void initView() {
        zhuceUserName = (EditText) findViewById(R.id.zhuce_userName);
        zhucePassword = (EditText) findViewById(R.id.zhuce_passWord);
        zc = (Button) findViewById(R.id.zhuce);
        zhuceback = (Button) findViewById(R.id.back);
    }
    private void initEvent() {
        // 绑定单击事件
        zhuceback.setOnClickListener(this);
        zc.setOnClickListener(this);
        //rule.setOnClickListener(this);

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
