package gjFragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easytablemore.BaseFragment;
import com.example.easytablemore.HttpCallbackListener;
import com.example.easytablemore.HttpUtil;
import com.example.easytablemore.LoginActivity;
import com.example.easytablemore.MainActivity;
import com.example.easytablemore.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import message.Constant;
import utils.Utility;

public class jwcjFragment extends BaseFragment implements View.OnClickListener {

    private View rootview;
    private  Button button;
    private EditText editUserName,kc,show_cj;
    private TextView text_currentTerm;
    // ÃÜÂë
    private EditText editPassword;
    private SharedPreferences pref;
    private TextView timeDetailCurrent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_jwcj, container,
                false);
        // TODO Auto-generated method stub
        return rootview;
    }

    @Override
    protected void initView(View view) {
        // TODO Auto-generated method stub
        super.initView(view);
        kc = (EditText) rootview.findViewById(R.id.edit_kc);
        button=(Button)rootview.findViewById(R.id.sqlcjcx);
        editUserName = (EditText) rootview.findViewById(R.id.edit_userName);
        show_cj = (EditText) rootview.findViewById(R.id.show_cj);

    }

    @Override
    protected void initEvents() {
        // TODO Auto-generated method stub
        super.initEvents();

        button.setOnClickListener(this);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.image_back).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getFragmentManager().popBackStack();
                    }
                });
    }

    @Override
    public void onClick(View v) {
        String editusername = editUserName.getText().toString().trim();
        String kcmc = kc.getText().toString().trim();
        switch(v.getId())
        {
            case R.id.sqlcjcx:
            {
                String web = "http://192.168.43.166:8080/web/user/get_grades.jsp?name="+ editusername + "&kc=" + kcmc;
                HttpUtil.sendRequest(web,
                        new HttpCallbackListener() {
                            @Override
                            public void onFinish(String response) {
                                show_cj.setText(response);
                                //showResult(kc.getText().toString() + "³É¼¨Îª£º" + response);
                                //Gson gson = new Gson();
                                //Grades res = (Grades)gson.fromJson(response, new TypeToken<Grades>(){}.getType());
//                                if (editPassword.getText().toString().equals("math"))
////                                    show_cj.setText(res.getMath());
////                                else if (editPassword.getText().toString().equals("english"))
////                                    show_cj.setText(res.getEnglish());
////                                else if (editPassword.getText().toString().equals("chinese"))
////                                    show_cj.setText(res.getChinese());
                            }
                            @Override
                            public void onError(Exception ex) {
                                ex.printStackTrace();
                            }
                        });
                break;
            }
            default:
                break;
        }

    }

    private void showResult(final String result){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity().getApplicationContext(),result,Toast.LENGTH_LONG).show();
            } });
    }


}
